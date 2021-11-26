import * as data from "../data/data";

export function bindSaveButton(values: data.Values) {
    const saveButton = document.getElementById("SAVE")
    if (saveButton) saveButton.addEventListener('click', _ => {
        const content = { values: values.asArray() }
        const anchor = document.createElement('a')
        const file   = new Blob([JSON.stringify(content)], {type : 'application/json'})
    
        anchor.href = URL.createObjectURL(file)
        anchor.download = "dsa.json"
        anchor.click()
    
        URL.revokeObjectURL(anchor.href)
    }); else console.warn(`Can't bind Speichern button - no HTMLElement for ID 'SAVE'`)
}
