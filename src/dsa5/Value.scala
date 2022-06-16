package dsa5

trait Value {
  def value: Int
  protected var observers: Vector[Int => Any] = Vector[Int => Any]()
  final def observe(f: Int => Any): Unit = observers +:= f
}

abstract class SettableValue(initialValue: Int) extends Value {
  private var _value: Int = initialValue
  override final def value: Int = _value
  protected def validate(newValue: Int): Boolean
  final def set(newValue: Int): Boolean =
    if (validate(newValue)) {
      _value = newValue
      observers.foreach(_(newValue))
      true
    } else false
}

final class Grundwert(initialValue: Int) extends SettableValue(initialValue) { grundwert =>
  override protected def validate(newValue: Int): Boolean = newValue >= 8 && newValue <= 19
  assert(validate(value), s"Initial value $initialValue not valid.")
  val ap: Value = new Value {
    override def value: Int = gw_ap(grundwert.value)
    grundwert.observe(_ => observers.foreach(_(value)))
  }
}
