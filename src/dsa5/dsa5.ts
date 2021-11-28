import * as data from "../data/data";

export const Grundwerte = ["MU","KL","IN","CH","FF","GE","KO","KK"]
export const Abenteuerpunkte = "AP"
export const AP_Grundwerte = "GWAP"
export const AP_Summanden = [AP_Grundwerte]

const ap_für_gw = [0,0,0,0,0,0,0,0,0, 15,30,45,60,75,90, 120,165,225,300,390]

export function Summe_AP(values: data.Values): number {
    return AP_Summanden
        .map(label => values.val(label))
        .reduce((a,b) => a+b)
}

function AP_Grundwert(value: number): number {
    const raw = ap_für_gw[value]
    if (raw || raw == 0) return raw; else {
        console.warn(`Can't calculate AP für Grundwert ${value}`)
        return 999999
    }
}

export function Summe_AP_Grundwerte(values: data.Values): number {
    return Grundwerte
        .map(label => values.val(label))
        .map(value => AP_Grundwert(value))
        .reduce((a,b) => a+b)
}
