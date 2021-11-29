export type Observer = (value: number) => void

export class Werte {
    private readonly values: Map<string, [number, Observer[]]> = new Map()

    private getOrInit(key: string): [number, Observer[]] {
        const value = this.values.get(key)
        if (value) return value; else { this.values.set(key, [0, []]); return this.getOrInit(key) }
    }

    val(key: string): number {
        return this.getOrInit(key)[0]
    }

    setze(key: string, value: number): void {
        const entry = this.getOrInit(key)
        entry[0] = value; entry[1].forEach(observer => observer(value))
    }

    beobachte(key: string, observer: Observer): void {
        const entry = this.getOrInit(key)
        entry[1].push(observer); observer(entry[0])
    }

    asArray(): Array<[string, number]> {
        return Array.from(this.values, x => [x[0], x[1][0]])
    }
}
