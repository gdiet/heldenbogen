package dsa5

import dsa5.HtmlUtils._

object Bogenelemente {
  def grundwerte(dsa: DSA5): Unit = {
    implicit val context: Elem = body
    table { id("Grundwerte")
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

  def talentwerte(dsa: DSA5): Unit = {
    implicit val context: Elem = body
    DSA5.talente.foreach { case (bereich, talente) =>
      val spalten = Seq("Talent", "Probe", "Werte", "Behinderung", "Steigerungsfaktor", "TW", "AP")
      val s_titel = Seq(bereich,  ""     ,      "", "BE"         , "SF"               , "TW", "AP")
      table { clazz("Talentwerte")
        colgroup(spalten.foreach(spalte => col(clazz(spalte))))
        tr(s_titel.foreach(titel => th(append(titel))))
      }
    }
  }
}
