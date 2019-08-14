import {save} from "./SaveHolder.js";

export function createEditPanel() {
    while(root.childElementCount > 0) {
        root.removeChild(root.lastChild);
    }

    const card = document.createElement('div');
    card.className = 'card px-4 py-4';
    card.style = 'background-color: #FFFFFF';

    const cardForm = document.createElement('form');
    cardForm.id = 'card-form';

    const col = document.createElement('div');
    col.className = 'col';

    let row = document.createElement('div');
    row.className = 'row justify-content-end';

    const text = document.createElement('p');
    text.innerText = '* required fields';
    row.appendChild(text);
    col.appendChild(row);

    row = document.createElement('div');
    row.className = 'row';

    const col_left = document.createElement('div');
    col_left.className = 'col-6';
    const col_right = document.createElement('div');
    col_right.className = 'col-6';

    row.appendChild(col_left);
    row.appendChild(col_right);
    col.appendChild(row);
    cardForm.appendChild(col);
    card.appendChild(cardForm);
    root.appendChild(card);

    let form = document.createElement('div');
    form.className = 'form-group';

    const photoLabel = document.createElement('label');
    photoLabel.for = 'photo';
    photoLabel.innerText = 'Photo *';

    const fileForm = document.createElement('div');
    fileForm.className = 'custom-file';
    fileForm.id = 'photo';

    const fileLabel = document.createElement('label');
    fileLabel.className = 'custom-file-label';
    fileLabel.for = 'file';
    fileLabel.innerText = 'Choose file';

    const fileField = document.createElement('input');
    fileField.className = 'custom-file-input';
    fileField.id = 'file';
    fileField.type = 'file';
    fileField.accept = 'image/*';
    fileField.required = true;

    fileForm.appendChild(fileLabel);
    fileForm.appendChild(fileField);
    form.appendChild(photoLabel);
    form.appendChild(fileForm);
    col_left.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const photographerLabel = document.createElement('label');
    photographerLabel.for = 'photographer';
    photographerLabel.innerText = 'Photographer';

    const photographerField = document.createElement('input');
    photographerField.className = 'form-control';
    photographerField.type = 'text';
    photographerField.id = 'photographer';

    form.appendChild(photographerLabel);
    form.appendChild(photographerField);
    col_left.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const dateLabel = document.createElement('label');
    dateLabel.for = 'date';
    dateLabel.innerText = 'Date';

    const dateField = document.createElement('input');
    dateField.className = 'form-control';
    dateField.type = 'text';
    dateField.id = 'date';

    form.appendChild(dateLabel);
    form.appendChild(dateField);
    col_left.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const airportLabel = document.createElement('label');
    airportLabel.for = 'airport';
    airportLabel.innerText = 'Airport';

    const airportField = document.createElement('input');
    airportField.className = 'form-control';
    airportField.type = 'text';
    airportField.id = 'airport';

    form.appendChild(airportLabel);
    form.appendChild(airportField);
    col_left.appendChild(form);

    row = document.createElement('div');
    row.className = 'row';
    col_left.appendChild(row);

    const col_left_sm = document.createElement('div');
    col_left_sm.className = 'col-6';
    row.appendChild(col_left_sm);

    const col_right_sm = document.createElement('div');
    col_right_sm.className = 'col-6';
    row.appendChild(col_right_sm);

    form = document.createElement('div');
    form.className = 'form-group';

    const iataCodeLabel = document.createElement('label');
    iataCodeLabel.for = 'iata_code';
    iataCodeLabel.innerText = 'IATA code (3 letters)';

    const iataCodeField = document.createElement('input');
    iataCodeField.className = 'form-control';
    iataCodeField.type = 'text';
    iataCodeField.id = 'iata_code';

    form.appendChild(iataCodeLabel);
    form.appendChild(iataCodeField);
    col_left_sm.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const icaoCodeLabel = document.createElement('label');
    icaoCodeLabel.for = 'icao_code';
    icaoCodeLabel.innerText = 'ICAO code (4 letters)';

    const icaoCodeField = document.createElement('input');
    icaoCodeField.className = 'form-control';
    icaoCodeField.type = 'text';
    icaoCodeField.id = 'icao_code';

    form.appendChild(icaoCodeLabel);
    form.appendChild(icaoCodeField);
    col_right_sm.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const countryLabel = document.createElement('label');
    countryLabel.for = 'country';
    countryLabel.innerText = 'Country';

    const countryField = document.createElement('input');
    countryField.className = 'form-control';
    countryField.type = 'text';
    countryField.id = 'country';

    form.appendChild(countryLabel);
    form.appendChild(countryField);
    col_right.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const planeLabel = document.createElement('label');
    planeLabel.for = 'plane';
    planeLabel.innerText = 'Plane *';

    const planeField = document.createElement('input');
    planeField.className = 'form-control';
    planeField.type = 'text';
    planeField.id = 'plane';
    planeField.required = true;

    form.appendChild(planeLabel);
    form.appendChild(planeField);
    col_right.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const regNumberLabel = document.createElement('label');
    regNumberLabel.for = 'reg_number';
    regNumberLabel.innerText = 'Registration number *';

    const regNumberField = document.createElement('input');
    regNumberField.className = 'form-control';
    regNumberField.type = 'text';
    regNumberField.id = 'reg_number';
    regNumberField.required = true;

    form.appendChild(regNumberLabel);
    form.appendChild(regNumberField);
    col_right.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const serialNumberLabel = document.createElement('label');
    serialNumberLabel.for = 'serial_number';
    serialNumberLabel.innerText = 'Serial number';

    const serialNumberField = document.createElement('input');
    serialNumberField.className = 'form-control';
    serialNumberField.type = 'text';
    serialNumberField.id = 'serial_number';

    form.appendChild(serialNumberLabel);
    form.appendChild(serialNumberField);
    col_right.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const airlineLabel = document.createElement('label');
    airlineLabel.for = 'airline';
    airlineLabel.innerText = 'Airline';

    const airlineField = document.createElement('input');
    airlineField.className = 'form-control';
    airlineField.type = 'text';
    airlineField.id = 'airline';

    form.appendChild(airlineLabel);
    form.appendChild(airlineField);
    col_right.appendChild(form);

    row = document.createElement('div');
    row.className = 'row justify-content-center mt-3';

    const button = document.createElement('button');
    button.className = 'btn btn-outline-primary';
    button.innerText = 'Save data';
    button.addEventListener('click', function (event) {
        event.preventDefault();
        save();
    });
    row.appendChild(button);
    col.appendChild(row);
}