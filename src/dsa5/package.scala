package object dsa5 {
  // The "tap" method will be available in the Scala 3 standard library.
  implicit class RichObject[T] (val t: T) extends AnyVal {
    def tap(f: T => Any): T = { f(t); t }
  }
}
