package dsa5

/** Implementations MUST call the observers on value changes. */
trait Value {
  def value: String
  protected var observers: Vector[Value => Any] = Vector()
  /** Registers AND IMMEDIATELY CALLS the observer. */
  final def observe(f: Value => Any): Unit = { observers +:= f; f(this) }
}

abstract class SettableValue(initialValue: String) extends Value {
  private var _value: String = initialValue
  override final def value: String = _value
  def ap: Value
  protected def validate(newValue: String): Boolean
  final def set(newValue: String): Boolean =
    if (validate(newValue)) {
      _value = newValue
      observers.foreach(_(this))
      true
    } else false
}

final class Texteingabe() extends SettableValue("") {
  override def ap: Value = new Value { override def value: String = "0" }
  override protected def validate(newValue: String): Boolean = true
}

final class Grundwert() extends SettableValue("10") { grundwert =>
  override protected def validate(newValue: String): Boolean =
    newValue.toIntOption.exists(gw => gw >= 8 && gw <= 19)
  val ap: Value = new Value {
    override def value: String = DSA5.gw_ap(grundwert.value.toInt)
    grundwert.observe(_ => observers.foreach(_(this)))
  }
}

final class Talentwert(steigerungsFaktor: Int) extends SettableValue("0") { talentwert =>
  override protected def validate(newValue: String): Boolean =
    newValue.toIntOption.exists(tw => tw >= 0 && tw <= 20)
  val ap: Value = new Value {
    override def value: String = (DSA5.talente_ap(talentwert.value.toInt) * steigerungsFaktor).toString
    talentwert.observe(_ => observers.foreach(_(this)))
  }
}

final class Abenteuerpunkte extends Value {
  var summanden: Vector[Value] = Vector()
  def plus(value: Value): Unit = { summanden +:= value; value.observe(_ => observers.foreach(_(this))) }
  override def value: String = summanden.map(_.value.toInt).sum.toString
}
