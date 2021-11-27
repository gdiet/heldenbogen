import * as bind from "./bind/bind.js"
import * as data from "./data/data.js"
import * as dsa5 from "./dsa5/dsa5.js"

const Werte = new data.Values

dsa5.Grundwerte.forEach(Grundwert => bind.bindNumberInput(Werte, Grundwert, 10))
dsa5.Grundwerte.forEach(Grundwert => Werte.observe(Grundwert, _ => bind.setNumberContent("GWAP", dsa5.Summe_AP_Grundwerte(Werte))))
bind.bindSaveButton(Werte)
bind.bindLoadButton(Werte)

const Körpertalente: [string, string, boolean, number][] = [
  ["Körperbeherrschung", "GE/GE/KO", true, 4],
  ["Taschendiebstahl",   "MU/FF/GE", true, 2],
]

const kt = document.getElementById("Körpertalente")
if (kt instanceof HTMLTableElement) {
  Körpertalente.forEach(([Name, Probe, Behinderung, Faktor]) => {
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
    row.insertCell().appendChild(input)
  })
}
