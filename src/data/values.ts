export type Observer = (value: number) => void

export class Values {
    readonly _values: Map<string, [number, Observer[]]> = new Map()

    _getOrInit(key: string): [number, Observer[]] {
        const value = this._values.get(key)
        if (value) return value; else { this._values.set(key, [0, []]); return this._getOrInit(key) }
    }

    val(key: string): number {
        return this._getOrInit(key)[0]
    }

    set(key: string, value: number): void {
        const entry = this._getOrInit(key)
        entry[0] = value; entry[1].forEach(observer => observer(value))
    }

    observe(key: string, observer: Observer): void {
        const entry = this._getOrInit(key)
        entry[1].push(observer); observer(entry[0])
    }

    asArray(): Array<[string, number]> {
        return Array.from(this._values, x => [x[0], x[1][0]])
    }
}
