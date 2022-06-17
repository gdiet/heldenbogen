package dsa5

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.html.Input

object Bogenelemente {
  def grundwerte(): Map[String, Grundwert] = {
    val table = document.createElement("table")
    table.id = "Grundwerte"
    document.body.append(table)
    val tr = document.createElement("tr")
    table.append(tr)

    gw_keys.map { name =>
      val gw -> input = grundwert(name)
      val td = document.createElement("td")
      td.append(s"$name ", input)
      tr.append(td)
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
