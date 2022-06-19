package dsa5

/** Implementations MUST call the observers on value changes. */
trait Value {
  def value: Int
  protected var observers: Vector[Value => Any] = Vector()
  /** Registers AND IMMEDIATELY CALLS the observer. */
  final def observe(f: Value => Any): Unit = { observers +:= f; f(this) }
}

abstract class SettableValue(initialValue: Int) extends Value {
  private var _value: Int = initialValue
  override final def value: Int = _value
  def ap: Value
  protected def validate(newValue: Int): Boolean
  final def set(newValue: Int): Boolean =
    if (validate(newValue)) {
      _value = newValue
      observers.foreach(_(this))
      true
    } else false
}

final class Grundwert(initialValue: Int) extends SettableValue(initialValue) { grundwert =>
  override protected def validate(newValue: Int): Boolean = newValue >= 8 && newValue <= 19
  assert(validate(value), s"Initial value $initialValue not valid.")
  val ap: Value = new Value {
    override def value: Int = DSA5.gw_ap(grundwert.value)
    grundwert.observe(_ => observers.foreach(_(this)))
  }
}

final class Talentwert(initialValue: Int) extends SettableValue(initialValue) { talentwert =>
  override protected def validate(newValue: Int): Boolean = newValue >= 0 && newValue <= 20
  assert(validate(value), s"Initial value $initialValue not valid.")
  val ap: Value = new Value {
    override def value: Int = DSA5.talente_ap(talentwert.value)
    talentwert.observe(_ => observers.foreach(_(this)))
  }
}

final class Abenteuerpunkte extends Value {
  var summanden: Vector[Value] = Vector()
  def plus(value: Value): Unit = { summanden +:= value; value.observe(_ => observers.foreach(_(this))) }
  override def value: Int = summanden.map(_.value).sum
}
