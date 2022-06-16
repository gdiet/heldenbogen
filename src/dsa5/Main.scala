package dsa5

import org.scalajs.dom
import org.scalajs.dom.document

object Main {

  def main(args: Array[String]): Unit =
    document.addEventListener("DOMContentLoaded", { _: dom.Event => setupUI() })

  def setupUI(): Unit = {
    val gw_mu = new Grundwert(10)
    val mu = document.createElement("input").asInstanceOf[dom.html.Input]
    mu.id = "MU"
    mu.`type` = "number"
    mu.min = "8"
    mu.max = "19"
    mu.value = gw_mu.value.toString
    mu.addEventListener("input", { _: dom.Event =>
      if (!mu.value.toIntOption.exists(gw_mu.set)) mu.value = gw_mu.value.toString
    })
    document.body.append("MU ")
    document.body.appendChild(mu)
  }
}
