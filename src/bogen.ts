import * as bind from "./bind/bind.js"
import * as data from "./data/data.js"
import * as dsa5 from "./dsa5/dsa5.js"

const Werte = new data.Values

dsa5.Grundwerte.forEach(Grundwert => bind.bindNumberInput(Werte, Grundwert, 10))
dsa5.Grundwerte.forEach(Grundwert => Werte.observe(Grundwert, _ => Werte.set(dsa5.AP_Grundwerte, dsa5.Summe_AP_Grundwerte(Werte))))
Werte.observe(dsa5.AP_Grundwerte, gwap => bind.setNumberContent(dsa5.AP_Grundwerte,gwap))
dsa5.AP_Summanden.forEach(Summand => Werte.observe(Summand, _ => Werte.set(dsa5.Abenteuerpunkte, dsa5.Summe_AP(Werte))))
Werte.observe(dsa5.Abenteuerpunkte, ap => bind.setNumberContent(dsa5.Abenteuerpunkte, ap))
bind.bindSaveButton(Werte)
bind.bindLoadButton(Werte)

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
    bind.bindNumberInputElement(Werte, Name, input, 0)
    Werte.observe(Name, _ => Werte.set(dsa5.AP_Körpertalente, dsa5.Summe_AP_Talente(Werte, dsa5.Körpertalente)))
    row.insertCell().appendChild(input)
  })
}
Werte.observe(dsa5.AP_Körpertalente, ap => bind.setNumberContent(dsa5.AP_Körpertalente,ap))
