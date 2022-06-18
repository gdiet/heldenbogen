package dsa5

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.html.Input

object Bogenelemente {
  def grundwerte(): Map[String, Grundwert] = {
    val table = HtmlUtils.table("Grundwerte")
    val tr = HtmlUtils.tr(table)
    DSA5.gw_keys.map { name =>
      val gw -> input = grundwert(name)
      tr.append(HtmlUtils.td(s"$name ", input))
      name -> gw
    }.toMap
  }
  private def grundwert(name: String): (Grundwert, Input) = {
    val gw = new Grundwert(10)
    val input = document.createElement("input").asInstanceOf[dom.html.Input]
    input.id = name
    input.`type` = "number"
    input.min = "8"
    input.max = "19"
    input.value = gw.value.toString
    input.addEventListener("input", { _: dom.Event =>
      if (!input.value.toIntOption.exists(gw.set)) input.value = gw.value.toString
    })
    gw -> input
  }

  def abenteuerpunkte_uebersicht(ap_grundwerte: Abenteuerpunkte): Unit = {
    val ap_gw_td = HtmlUtils.td()
    val ap_table = HtmlUtils.table("AP_Übersicht").tap(HtmlUtils.tr(_).append(ap_gw_td))
    ap_grundwerte.observe(_ => ap_gw_td.replaceChildren(s"AP Grundwerte: ${ap_grundwerte.value}"))
    document.body.append(ap_table)
  }

  def talentwerte(kategorie: String): Unit = {
    val table = HtmlUtils.table(s"${kategorie}talente")
    val cols = Seq("Talent", "GW", "Probe", "BE", "x", "TW", "AP")
    cols.foreach(id => HtmlUtils.col(s"${kategorie}_$id").tap(table.append(_)))
    val headerRow = HtmlUtils.tr(table)
    cols.foreach(col => HtmlUtils.th(col).tap(headerRow.append(_)))
    DSA5.talente(kategorie).foreach { case (talent, probe, behinderung, steigerungsfaktor) =>
      val tr = HtmlUtils.tr(table)
      Seq(talent, probe, "", behinderung, s"$steigerungsfaktor", "", "").foreach(col =>
        HtmlUtils.td(col).tap(tr.append(_))
      )
    }
    document.body.append(table)
//  Ausblenden von Spalten:
//    document.getElementById(s"${kategorie}_GW").setAttribute("style", "visibility:collapse")
    // Vermutlich besser wenn wir class="spalte_gw" definieren und darüber CSS "visibility:collapse"
  }
}
