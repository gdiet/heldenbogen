package dsa5

import org.scalajs.dom
import org.scalajs.dom.document

object Main {

  def main(args: Array[String]): Unit =
    document.addEventListener("DOMContentLoaded", { _: dom.Event => setupUI() })

  def setupUI(): Unit = {
    val dsa = new DSA5()
    Bogenelemente.grundwerte(dsa)
    Bogenelemente.talentwerte(dsa)
  }
}
