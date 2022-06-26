package dsa5

import org.scalajs.dom
import org.scalajs.dom.document

object Main {

  def main(args: Array[String]): Unit =
    document.addEventListener("DOMContentLoaded", { _: dom.Event => setupUI() })

  def setupUI(): Unit = {
    val dsa = new DSA5()
    Bogenelemente.bogenkopf(dsa)
    Bogenelemente.grundwerte(dsa)
    Bogenelemente.spalten_selektoren()
    Bogenelemente.talentwerte(dsa)
    Bogenelemente.speichern_laden(dsa)
  }
}
