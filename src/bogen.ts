import * as bind from "./bind/bind.js"
import * as data from "./data/data.js"
import * as dsa5 from "./dsa5/dsa5.js"

const Werte = new data.Values

dsa5.Grundwerte.forEach(Grundwert => bind.bindNumberInput(Werte, Grundwert, 10))
dsa5.Grundwerte.forEach(Grundwert => Werte.observe(Grundwert, _ => bind.setNumberContent("GWAP", dsa5.Summe_AP_Grundwerte(Werte))))
bind.bindSaveButton(Werte)
bind.bindLoadButton(Werte)
