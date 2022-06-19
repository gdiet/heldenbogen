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
      val spalten = Seq("Talent", "Probe", "Werte", "Behinderung", "Steigerungsfaktor", "Talentwert", "Abenteuerpunkte")
      val s_titel = Seq(bereich ,  ""    , ""     , "BE"         , "SF"               , "TW"        , "AP"             )
      table { clazz("Talentwerte")
        colgroup(spalten.foreach(spalte => col(clazz(spalte))))
        tr {
          spalten.zip(s_titel).foreach { case (spalte, titel) =>
            th { clazz(spalte); append(titel) }
          }
        }
        talente.foreach { case (talent, probe, be, sf) =>
          tr {
            td { clazz(spalten(0)); append(talent) }
            td { clazz(spalten(1)); append(probe) }
            td { clazz(spalten(2)); append("??/??/??") }
            td { clazz(spalten(3)); append(be) }
            td { clazz(spalten(4)); append(s"$sf") }
            td { clazz(spalten(5)); append("??") }
            td { clazz(spalten(6)); append("??") }
          }
        }
      }
    }
  }
}
