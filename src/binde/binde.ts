import * as data from "../daten/data.js"
import * as dsa5 from "../dsa5/dsa5.js"
export * from "./load.js"
export * from "./save.js"

export function zahlenEingabeElement(values: data.Werte, label: string, input: HTMLInputElement, initialValue: number): void {
    values.setze(label, initialValue)
    values.beobachte(label, value => input.value = `${value}`)
    input.addEventListener("input", _ => {
        if (input.validity.valid) values.setze(label, parseInt(input.value))
        else input.value = `${values.val(label)}`
    })
}

export function zahlenEingabe(values: data.Werte, label: string, initialValue: number): void {
    const input = document.getElementById(label)
    if (input instanceof HTMLInputElement) zahlenEingabeElement(values, label, input, initialValue)
    else console.warn(`Can't bind - no HTMLInputElement for ID '${label}'`)
}

export function zahlenAusgabe(id: string, number: number): void {
    const element = document.getElementById(id)
    if (element) element.textContent = `${number}`
    else console.warn(`Can't show number - no HTMLElement for ID '${id}'`)
}

export function APfür(talentkategorie: dsa5.Talentkategorie) : string { return `AP-${talentkategorie.kategorie}` }

export function talentTabelle(talentkategorie: dsa5.Talentkategorie, werte: data.Werte): void {
    const kt = document.getElementById(talentkategorie.kategorie)
    if (kt instanceof HTMLTableElement) {
        talentkategorie.talente.forEach(([Name, Probe, Behinderung, Faktor]) => {
        const row = kt.insertRow()
        row.insertCell().textContent = Name
        row.insertCell().textContent = Probe
        row.insertCell().textContent = Behinderung
        row.insertCell().textContent = String.fromCharCode(64 + Faktor)
        const input = document.createElement("input")
        input.type = "number"
        input.value = "0"
        input.min = "0"
        input.max = "20"
        zahlenEingabeElement(werte, Name, input, 0)
        werte.beobachte(Name, _ => werte.setze(APfür(talentkategorie), dsa5.Summe_AP_Talente(werte, talentkategorie.talente)))
        row.insertCell().appendChild(input)
      })
    }
    werte.beobachte(dsa5.AP_Körpertalente, ap => zahlenAusgabe(APfür(talentkategorie), ap))
}
