import * as data from "../data/data";

export function bindLoadButton(values: data.Values) {
    const loadButton = document.getElementById("LOAD")
    if (loadButton instanceof HTMLInputElement) {
        loadButton.onchange = _ => {
            if (loadButton.files && loadButton.files[0]) {
                const reader = new FileReader()
                reader.onload = _ => {
                    const read = reader.result?.toString() || "{}"
                    console.log(JSON.parse(read))
                    values // FIXME
                }
                reader.readAsText(loadButton.files[0])
            } else console.warn("Laden button did not have expected file content")
        }
    } else console.warn(`Can't bind Laden button - no HTMLElement for ID 'LOAD'`)
}
