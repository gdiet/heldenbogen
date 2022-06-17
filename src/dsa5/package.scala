package object dsa5 {
  val gw_keys: Array[String] = Array("MU","KL","IN","CH","FF","GE","KO","KK")
  // Abenteuerpunkte für Grundwerte: Bis 8 -> 0 AP, maximaler Grundwert 19
  val gw_ap: Array[Int] = Array(0,0,0,0,0,0,0,0,0, 15,30,45,60,75,90, 120,165,225,300,390)

  implicit class RichObject[T] (t: T) {
    def tap(f: T => Any): T = { f(t); t }
  }
}
