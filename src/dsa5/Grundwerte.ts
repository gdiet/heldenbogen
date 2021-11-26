import * as data from "../data/data";

export const Grundwerte = ["MU","KL","IN","CH","FF","GE","KO","KK"]

const ap_fuer_gw = [0,0,0,0,0,0,0,0,0, 15,30,45,60,75,90, 120,165,225,300,390]

function AP_Grundwert(value: number): number {
    const raw = ap_fuer_gw[value]
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
