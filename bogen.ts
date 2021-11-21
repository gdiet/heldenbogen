// watch & compile: npx tsc -w

type Observer = (value: number) => void

class Values {
    readonly _values: Map<string, [number, Observer[]]> = new Map()

    get(key: string): [number, Observer[]] {
        if (this._values.has(key)) return this._values.get(key)
        else {
            const value: [number, Observer[]] = [0, []]
            this._values.set(key, value)
            return value
        }
    }

    val(key: string): number {
        return this.get(key)[0]
    }

    set(key: string, value: number): void {
        const entry = this.get(key)
        entry[0] = value
        entry[1].forEach(observer => observer(value))
    }

    asArray(): Array<[string, number]> {
        return Array.from(this._values, x => [x[0], x[1][0]])
    }
}

const vv = new Values
console.log(vv.val("MU"))
vv.set("MU", 4)
console.log(vv.get("MU")[0])
console.log(vv.asArray())
