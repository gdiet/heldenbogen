package dsa5

object Bogenelemente {
  def grundwerte(dsa: DSA5): Unit = {
    org.scalajs.dom.document.body.tap { body =>
      body._child("table")._id("Grundwerte").tap { table =>
        table._child("tr").tap { tr =>
          DSA5.gw_keys.foreach { gw =>
            tr._child("td").append(s"$gw ", "input")
          }
        }
      }
    }
  }
}
