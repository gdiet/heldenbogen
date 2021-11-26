import * as data from "../data/data";

type FileContent = {
    values: [string, number][]
}

export function bindLoadButton(values: data.Values) {
    const loadButton = document.getElementById("LOAD")
    if (loadButton instanceof HTMLInputElement) {
        loadButton.onchange = _ => {
            if (loadButton.files && loadButton.files[0]) {
                const reader = new FileReader()
                reader.onload = _ => {
                    const read = reader.result?.toString() || "{}"
                    const data: FileContent = JSON.parse(read)
                    data.values.forEach(kv => values.set(kv[0], kv[1]))
                }
                reader.readAsText(loadButton.files[0])
            } else console.warn("Laden button did not have expected file content")
        }
    } else console.warn(`Can't bind Laden button - no HTMLElement for ID 'LOAD'`)
}
