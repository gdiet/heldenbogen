package dsa5

import org.scalajs.dom
import org.scalajs.dom.document

object Main {

  def main(args: Array[String]): Unit =
    document.addEventListener("DOMContentLoaded", { _: dom.Event => setupUI() })

  def setupUI(): Unit = {
    val ap_grundwerte = new Abenteuerpunkte()
    Bogenelemente.grundwerte().tap(_.values.foreach(gw => ap_grundwerte.plus(gw.ap)))
    val ap_gw_td = HtmlUtils.td()
    val ap_table = HtmlUtils.table("AP_Übersicht").tap(HtmlUtils.tr(_).append(ap_gw_td))
    ap_grundwerte.observe(_ => ap_gw_td.replaceChildren(s"AP Grundwerte: ${ap_grundwerte.value}"))
    document.body.append(ap_table)
  }
}
