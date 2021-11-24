import { Values } from "./data/values.js"
import { bindNumberInput } from "./bind/bind.js"

const vv = new Values
vv.observe("MU", (value) => { console.log(`observed ${value}`) })
bindNumberInput(vv, "MU", 10)

// console.log(vv.val("MU"))
// vv.set("MU", 4)
// console.log(vv._getOrInit("MU")[0])
// console.log(vv.asArray())
