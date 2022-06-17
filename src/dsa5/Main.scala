package dsa5

import org.scalajs.dom
import org.scalajs.dom.document

object Main {

  def main(args: Array[String]): Unit =
    document.addEventListener("DOMContentLoaded", { _: dom.Event => setupUI() })

  def setupUI(): Unit = {
    val ap_grundwerte = new Abenteuerpunkte()
    Bogenelemente.grundwerte().tap(_.values.foreach(gw => ap_grundwerte.plus(gw.ap)))
    Bogenelemente.abenteuerpunkte_uebersicht(ap_grundwerte)
  }
}
