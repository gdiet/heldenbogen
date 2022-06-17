package dsa5

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.html.Input

object Bogenelemente {
  def grundwerte(): Map[String, Grundwert] = {
    val table = HtmlUtils.table("Grundwerte")
    val tr = HtmlUtils.tr(table)
    gw_keys.map { name =>
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
}
