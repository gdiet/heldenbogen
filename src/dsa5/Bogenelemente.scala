package dsa5

import dsa5.HtmlUtils._

object Bogenelemente {
  def ap_gesamt(dsa: DSA5): Unit = {
    implicit val context: Elem = body
    p {
      val element = context() // Get the current context Element itself.
      dsa.berechnet(s"AP").observe(ap =>
        element.replaceChildren(s"Σ AP ${ap.value}")
      )
    }
  }

  def grundwerte(dsa: DSA5): Unit = {
    implicit val context: Elem = body
    table { id("Grundwerte")
      tr {
        th { attr("colspan", "8")
          val element = context() // Get the current context Element itself.
          dsa.berechnet(s"AP Grundwerte").observe(ap =>
            element.replaceChildren(s"Grundwerte Σ AP ${ap.value}")
          )
        }
      }
      tr {
        DSA5.gw_keys.foreach { gw_key =>
          td {
            append(s"$gw_key ")
            val gw = dsa.zahleingaben(gw_key)
            numberInput(8, 19, gw.value) { input =>
              if (!input.value.toIntOption.exists(gw.set)) input.value = gw.value.toString
            }
          }
        }
      }
    }
  }

  def spalten_selektoren(): Unit = {
    implicit val context: Elem = body
    // FIXME ein-/ausklappbar mit Überschrift
    p {
      val schalter = div {
        append("Talente: Sichtbare Spalten (hier klicken)")
        context()
      }
      div {
        checkbox("Zeige Grundwerte", labelRight = true)(box =>
          setClassVisibility("Grundwerte", if (box.checked) "" else "collapse")
        ); br
        checkbox("Zeige Probe", labelRight = true)(box =>
          setClassVisibility("Probe", if (box.checked) "" else "collapse")
        ); br
        checkbox("Zeige Steigerungsfaktor", labelRight = true)(box =>
          setClassVisibility("Steigerungsfaktor", if (box.checked) "" else "collapse")
        ); br
        checkbox("Zeige Abenteuerpunkte", labelRight = true)(box =>
          setClassVisibility("Abenteuerpunkte", if (box.checked) "" else "collapse")
        )
        val style = context().style
        schalter.onEvent("click")(
          if (style.display == "none") style.display = "" else style.display = "none"
        )
      }
    }
  }

  def talentwerte(dsa: DSA5): Unit = {
    implicit val context: Elem = body
    DSA5.talentTabelle.foreach { case (bereich, talente) =>
      val spalten = Seq("Talent", "Grundwerte", "Probe", "Behinderung", "Steigerungsfaktor", "Talentwert", "Abenteuerpunkte")
      table { clazz("Talentwerte")
        colgroup(spalten.foreach(spalte => col(clazz(spalte))))
        tr {
          th { clazz(spalten(0)); append(bereich) }
          th { clazz(spalten(1))
            val element = context() // Get the current context Element itself.
            dsa.berechnet(s"AP $bereich").observe(ap =>
              element.replaceChildren(s"Σ AP ${ap.value}")
            )
          }
          th { clazz(spalten(2)); append("Probe") }
          th { clazz(spalten(3)); append("BE") }
          th { clazz(spalten(4)); append("SF") }
          th { clazz(spalten(5)); append("TW") }
          th { clazz(spalten(6)); append("AP") }
        }
        talente.foreach { case (talent, probe, be, sf) =>
          val talentwert = dsa.zahleingaben(talent)
          tr {
            td { clazz(spalten(0)); append(talent) }
            td { clazz(spalten(1)); append(probe) }
            td { clazz(spalten(2))
              val element = context() // Get the current context Element itself.
              val werte = probe.split('/').map(dsa.zahleingaben)
              werte.foreach(_.observe(_ => element.replaceChildren(werte.map(_.value).mkString("/"))))
            }
            td { clazz(spalten(3)); append(be) }
            td { clazz(spalten(4)); append(s"$sf") }
            td { clazz(spalten(5))
              numberInput(0, 20, talentwert.value) { input =>
                if (!input.value.toIntOption.exists(talentwert.set)) input.value = talentwert.value.toString
              }
            }
            td { clazz(spalten(6))
              val element = context() // Get the current context Element itself.
              talentwert.observe(_ => element.replaceChildren(s"${talentwert.ap.value}") )
            }
          }
        }
      }
    }
  }

  def speichern_laden(dsa: DSA5): Unit = {
    implicit val context: Elem = body
    p {
      val dateiname = stringInput("Heldenbogen.json")
      append(" ")
      button("Speichern") {
        scala.scalajs.js.Dynamic.global.speichern(dateiname.value, dsa.zahleingabenJson)
      }
      append(" ")
      button("Laden") {
        println("Laden")
      }
    }
  }
}
