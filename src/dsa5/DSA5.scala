package dsa5

import dsa5.DSA5._

class DSA5 {
  var zahleingaben: Map[String, SettableValue] = Map()
  var berechnet: Map[String, Value] = Map()

  // Grundwerte und Abenteuerpunkte Grundwerte initialisieren
  new Abenteuerpunkte().tap { ap_grundwerte =>
    gw_keys.foreach { key =>
      zahleingaben += key -> new Grundwert(10).tap(ap_grundwerte.plus)
    }
    berechnet += "AP Grundwerte" -> ap_grundwerte
  }

  // Talentewerte und Abenteuerpunkte Talentwerte initialisieren
  talentTabelle.foreach { case (bereich, talente) =>
    new Abenteuerpunkte().tap { ap_bereich =>
      talente.foreach { case (talent, _, _, steigerungsfaktor) =>
        zahleingaben += talent -> new Talentwert(0, steigerungsfaktor).tap(tw => ap_bereich.plus(tw.ap))
      }
      berechnet += s"AP $bereich" -> ap_bereich
    }
  }

  // Gesamt-Abenteuerpunkte initialisieren
  new Abenteuerpunkte().tap { ap =>
    berechnet.foreach { case (key, value) => if (key.startsWith("AP ")) ap.plus(value) }
    berechnet += "AP" -> ap
  }
}

object DSA5 {
  def gw_keys: Array[String] = Array("MU","KL","IN","CH","FF","GE","KO","KK")

  // Abenteuerpunkte für Grundwerte: Bis 8 -> 0 AP, maximaler Grundwert 19
  def gw_ap: Array[Int] = Array(0,0,0,0,0,0,0,0,0, 15,30,45,60,75,90, 120,165,225,300,390)

  // Abenteuerpunkte für Talentwerte, maximaler Talentwert 20
  def talente_ap: Array[Int] = Array(0,1,2,3,4,5,6,7,8,9,10,11,12, 14,17,21,26,32,39,47,56)

  // Talente: Talent, Probe, Behinderung, Steigerungsfaktor
  def talentTabelle: Seq[(String, Seq[(String, String, String, Int)])] = Seq(
    "Körpertalente" -> Seq(
      ("Fliegen",            "MU/IN/GE", "J", 2),
      ("Gaukeleien",         "MU/CH/FF", "J", 1),
      ("Klettern",           "MU/GE/KK", "J", 2),
      ("Körperbeherrschung", "GE/GE/KO", "J", 4),
      ("Kraftakt",           "KO/KK/KK", "J", 2),
      ("Reiten",             "CH/GE/KK", "J", 2),
      ("Schwimmen",          "GE/KO/KK", "J", 2),
      ("Selbstbeherrschung", "MU/MU/KO", "N", 4),
      ("Singen",             "KL/CH/KO", "?", 1),
      ("Sinnesschärfe",      "KL/IN/IN", "?", 4),
      ("Tanzen",             "KL/CH/GE", "J", 1),
      ("Taschendiebstahl",   "MU/FF/GE", "J", 2),
      ("Verbergen",          "MU/IN/GE", "J", 3),
      ("Zechen",             "KL/KO/KK", "N", 1),
    ),
    "Gesellschaftstalente" -> Seq(
      ("Bekehren/Überzeugen", "MU/KL/CH", "N", 2),
      ("Betören",             "MU/CH/CH", "?", 2),
      ("Einschüchtern",       "MU/IN/CH", "N", 2),
      ("Etikette",            "KL/IN/CH", "?", 2),
      ("Gassenwissen",        "KL/IN/CH", "?", 3),
      ("Menschenkenntnis",    "KL/IN/CH", "N", 3),
      ("Überreden",           "MU/IN/CH", "N", 3),
      ("Verkleiden",          "IN/CH/GE", "?", 2),
      ("Willenskraft",        "MU/IN/CH", "N", 4),
    )
  )
}
