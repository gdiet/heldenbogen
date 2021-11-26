import { Values } from "./data/values.js"
import { bindNumberInput } from "./bind/bind.js"

const vv = new Values

const grundwerte = ["MU","KL","IN","CH","FF","GE","KO","KK"]
grundwerte.forEach(grundwert => bindNumberInput(vv, grundwert, 10))

const ap_fuer_gw = [0,0,0,0,0,0,0,0,0, 15,30,45,60,75,90, 120,165,225,300,390]
function apfuergw(value: number): number {
    const raw = ap_fuer_gw[value]
    if (raw || raw == 0) return raw; else {
        console.warn(`Can't calculate AP für Grundwert ${value}`)
        return 999999
    }
}

function show_ap_summe_gw(): void {
    const ap =
        grundwerte
            .map(label => vv.val(label))
            .map(value => apfuergw(value))
            .reduce((a,b) => a+b)
    const gwap = document.getElementById("GWAP")
    if (gwap) gwap.textContent = `${ap}`
    else console.warn(`Can't show Summe AP für Grundwerte - no HTMLElement for ID 'GWAP'`)
}
grundwerte.forEach(grundwert => vv.observe(grundwert, _ => show_ap_summe_gw()))

const saveButton = document.getElementById("SAVE")
if (saveButton) {
    saveButton.addEventListener('click', () => {
        const anchor = document.createElement('a')
        const file   = new Blob([JSON.stringify({values: vv.asArray()})], {type : 'application/json'})
    
        anchor.href = URL.createObjectURL(file)
        anchor.download = "dsa.json"
        anchor.click()
        
        URL.revokeObjectURL(anchor.href)
      })
} else console.warn(`Can't bind Speichern button - no HTMLElement for ID 'SAVE'`)


// console.log(vv.val("MU"))
// vv.set("MU", 4)
// console.log(vv._getOrInit("MU")[0])
// console.log(vv.asArray())
