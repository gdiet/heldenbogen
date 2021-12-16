import { Talentkategorie } from "./dsa5"

export const Körpertalente: Talentkategorie = {
    kategorie: "Körpertalente",
    talente: [
        ["Fliegen",            "MU/IN/GE", "J", 2],
        ["Gaukeleien",         "MU/CH/FF", "J", 1],
        ["Klettern",           "MU/GE/KK", "J", 2],
        ["Körperbeherrschung", "GE/GE/KO", "J", 4],
        ["Kraftakt",           "KO/KK/KK", "J", 2],
        ["Reiten",             "CH/GE/KK", "J", 2],
        ["Schwimmen",          "GE/KO/KK", "J", 2],
        ["Selbstbeherrschung", "MU/MU/KO", "N", 4],
        ["Singen",             "KL/CH/KO", "?", 1],
        ["Sinnesschärfe",      "KL/IN/IN", "?", 4],
        ["Tanzen",             "KL/CH/GE", "J", 1],
        ["Taschendiebstahl",   "MU/FF/GE", "J", 2],
        ["Verbergen",          "MU/IN/GE", "J", 3],
        ["Zechen",             "KL/KO/KK", "N", 1],
    ]
}

export const Gesellschaftstalente: Talentkategorie = {
    kategorie: "Gesellschaftstalente",
    talente: [
        ["Bekehren & Überzeugen", "MU/KL/CH", "N", 2],
        ["Betören",               "MU/CH/CH", "?", 2],
        ["Einschüchtern",         "MU/IN/CH", "N", 2],
        ["Etikette",              "KL/IN/CH", "?", 2],
        ["Gassenwissen",          "KL/IN/CH", "?", 3],
        ["Menschenkenntnis",      "KL/IN/CH", "N", 3],
        ["Überreden",             "MU/IN/CH", "N", 3],
        ["Verkleiden",            "IN/CH/GE", "?", 2],
        ["Willenskraft",          "MU/IN/CH", "N", 4],
    ]
}
