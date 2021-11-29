import * as binde from "./binde/binde.js"
import * as daten from "./daten/data.js"
import * as dsa5 from "./dsa5/dsa5.js"

const Werte = new daten.Werte

dsa5.Grundwerte.forEach(Grundwert => binde.zahlenEingabe(Werte, Grundwert, 10))
dsa5.Grundwerte.forEach(Grundwert => Werte.beobachte(Grundwert, _ => Werte.setze(dsa5.AP_Grundwerte, dsa5.Summe_AP_Grundwerte(Werte))))
Werte.beobachte(dsa5.AP_Grundwerte, ap => binde.zahlenAusgabe(dsa5.AP_Grundwerte, ap))
dsa5.AP_Summanden.forEach(Summand => Werte.beobachte(Summand, _ => Werte.setze(dsa5.Abenteuerpunkte, dsa5.Summe_AP(Werte))))
Werte.beobachte(dsa5.Abenteuerpunkte, ap => binde.zahlenAusgabe(dsa5.Abenteuerpunkte, ap))
binde.speichern(Werte)
binde.laden(Werte)

const kt = document.getElementById("Körpertalente")
if (kt instanceof HTMLTableElement) {
  dsa5.Körpertalente.forEach(([Name, Probe, Behinderung, Faktor]) => {
    const row = kt.insertRow()
    row.insertCell().textContent = Name
    row.insertCell().textContent = Probe
    row.insertCell().textContent = Behinderung ? "Ja": "Nein"
    row.insertCell().textContent = String.fromCharCode(64 + Faktor)
    const input = document.createElement("input")
    input.type = "number"
    input.value = "0"
    input.min = "0"
    input.max = "20"
    binde.zahlenEingabeElement(Werte, Name, input, 0)
    Werte.beobachte(Name, _ => Werte.setze(dsa5.AP_Körpertalente, dsa5.Summe_AP_Talente(Werte, dsa5.Körpertalente)))
    row.insertCell().appendChild(input)
  })
}
Werte.beobachte(dsa5.AP_Körpertalente, ap => binde.zahlenAusgabe(dsa5.AP_Körpertalente,ap))
