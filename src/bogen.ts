import * as binde from "./binde/binde.js"
import * as daten from "./daten/data.js"
import * as dsa5 from "./dsa5/dsa5.js"

const Werte = new daten.Werte

dsa5.Grundwerte.forEach(Grundwert => binde.zahlenEingabe(Werte, Grundwert, 10))
dsa5.Grundwerte.forEach(Grundwert => Werte.beobachte(Grundwert, _ => Werte.setze(dsa5.AP_Grundwerte, dsa5.Summe_AP_Grundwerte(Werte))))
Werte.beobachte(dsa5.AP_Grundwerte, ap => binde.zahlenAusgabe(dsa5.AP_Grundwerte, ap))

dsa5.AP_Summanden.forEach(Summand => Werte.beobachte(Summand, _ => Werte.setze(dsa5.Abenteuerpunkte, dsa5.Summe_AP(Werte))))
Werte.beobachte(dsa5.Abenteuerpunkte, ap => binde.zahlenAusgabe(dsa5.Abenteuerpunkte, ap))

binde.talentTabelle(dsa5.Körpertalente, Werte)
binde.talentTabelle(dsa5.Gesellschaftstalente, Werte)

binde.speichern(Werte)
binde.laden(Werte)
