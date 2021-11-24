import { Values } from "../data/values";

export function bindNumberInput(values: Values, label: string, initialValue: number): void {
    const input = document.getElementById(label)
    if (input instanceof HTMLInputElement) {
        values.set(label, initialValue)
        values.observe(label, value => input.value = `${value}`)
        input.addEventListener("input", _ => {
            if (input.validity.valid) values.set(label, parseInt(input.value))
            else input.value = `${values.val(label)}`
          })
    } else console.warn(`Can't bind - no HTMLInputElement for ID ${label}`)
}
