import * as data from "../data/data";
export * from "./load.js"
export * from "./save.js"

export function bindNumberInputElement(values: data.Values, label: string, input: HTMLInputElement, initialValue: number): void {
    values.set(label, initialValue)
    values.observe(label, value => input.value = `${value}`)
    input.addEventListener("input", _ => {
        if (input.validity.valid) values.set(label, parseInt(input.value))
        else input.value = `${values.val(label)}`
    })
}

export function bindNumberInput(values: data.Values, label: string, initialValue: number): void {
    const input = document.getElementById(label)
    if (input instanceof HTMLInputElement) bindNumberInputElement(values, label, input, initialValue)
    else console.warn(`Can't bind - no HTMLInputElement for ID '${label}'`)
}

export function setNumberContent(id: string, number: number): void {
    const element = document.getElementById(id)
    if (element) element.textContent = `${number}`
    else console.warn(`Can't show number - no HTMLElement for ID '${id}'`)
}
