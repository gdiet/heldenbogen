package dsa5

import HtmlUtils._

object Bogenelemente {
  def grundwerte(dsa: DSA5): Unit = {
    implicit val context: Elem = body
    table { id("Grundwerte")
      tr {
        DSA5.gw_keys.foreach { gw =>
          td {
            append(s"$gw ")
          }
        }
      }
    }
  }
}
