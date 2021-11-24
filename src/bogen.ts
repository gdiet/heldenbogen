import { Values } from "./data/values.js"
import { bindNumberInput } from "./bind/bind.js"

const vv = new Values

const grundwerte = ["MU","KL","IN","CH","FF","GE","KO","KK"]
grundwerte.forEach(grundwert => bindNumberInput(vv, grundwert, 10))

// console.log(vv.val("MU"))
// vv.set("MU", 4)
// console.log(vv._getOrInit("MU")[0])
// console.log(vv.asArray())
