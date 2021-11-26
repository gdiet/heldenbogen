import * as data from "../data/data";

function getFileName(): string {
    const fileNameInput = document.getElementById("FILENAME")
    if (fileNameInput instanceof HTMLInputElement) return fileNameInput.value
    else {
        console.warn("Can't read file name from FILENAME element.")
        return "Heldenbogen.json"
    }
}

export function bindSaveButton(values: data.Values) {
    const saveButton = document.getElementById("SAVE")
    if (saveButton) saveButton.addEventListener('click', _ => {
        const content = { values: values.asArray() }
        const anchor = document.createElement('a')
        const file   = new Blob([JSON.stringify(content)], {type : 'application/json'})
    
        anchor.href = URL.createObjectURL(file)
        anchor.download = getFileName()
        anchor.click()
    
        URL.revokeObjectURL(anchor.href)
    }); else console.warn(`Can't bind Speichern button - no HTMLElement for ID 'SAVE'`)
}
