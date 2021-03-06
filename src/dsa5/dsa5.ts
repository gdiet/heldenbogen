import * as binde from "../binde/binde.js"
import * as data from "../daten/data.js"
import * as talente from "./Talente.js"
export * from "./Talente.js"

export const Grundwerte = ["MU","KL","IN","CH","FF","GE","KO","KK"]
export const Abenteuerpunkte = "AP"
export const AP_Grundwerte = "GWAP"
export const AP_Körpertalente = binde.APfür(talente.Körpertalente)
export const AP_Gesellschaftstalente = "AP-Gesellschaftstalente"
export const AP_Summanden = [AP_Grundwerte, AP_Körpertalente, AP_Gesellschaftstalente]

const ap_für_gw = [0,0,0,0,0,0,0,0,0, 15,30,45,60,75,90, 120,165,225,300,390]
const ap_für_talente = [0,1,2,3,4,5,6,7,8,9,10,11,12, 14,17,21,26,32,39,47,56]

type Talente = [string, string, string, number][]

export type Talentkategorie = {
    kategorie: string
    talente: Talente
}

export function Summe_AP(values: data.Werte): number {
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

function AP_Talent(value: number): number {
    const raw = ap_für_talente[value]
    if (raw || raw == 0) return raw; else {
        console.warn(`Can't calculate AP für Talentwert ${value}`)
        return 999999
    }
}

export function Summe_AP_Grundwerte(values: data.Werte): number {
    return Grundwerte
        .map(label => values.val(label))
        .map(value => AP_Grundwert(value))
        .reduce((a,b) => a+b)
}

export function Summe_AP_Talente(values: data.Werte, talente: Talente): number {
    return talente
        .map(([label, _1, _2, factor]) => AP_Talent(values.val(label)) * factor)
        .reduce((a,b) => a+b)
}
