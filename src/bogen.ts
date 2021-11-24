import { Values } from "./data/values.js"

const vv = new Values
vv.observe("MU", (value) => { console.log(`observed ${value}`) })
console.log(vv.val("MU"))
vv.set("MU", 4)
console.log(vv._getOrInit("MU")[0])
console.log(vv.asArray())
