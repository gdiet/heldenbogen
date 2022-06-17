package object dsa5 {
  implicit class RichObject[T] (t: T) {
    def tap(f: T => Any): T = { f(t); t }
  }
}
