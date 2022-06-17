package dsa5

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.html.Input

object Bogenelemente {
  def grundwerte(): Map[String, Grundwert] = {
    val p = document.createElement("p")
    val map = gw_keys.map(name => name -> grundwert(name))
    p.append(s"${gw_keys(0)} ", map.head._2._2)
    map.tail.foreach { case (key, (_, input)) => p.append(s" - $key ", input) }
    document.body.append(p)
    map.map { case (key, (gw, _)) => key -> gw }.toMap
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
