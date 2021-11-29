import * as data from "../daten/data";
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
