import {appUrl} from "./App.js";
import {deactivateNavbarLinks} from "./Navbar.js";
import {createPhotoPanel} from "./Photo.js";

export async function save() {
    const form = document.getElementById('card-form');
    const elements = form.getElementsByTagName('input');

    for(const i of elements) {
        if(i.value === '' && i.hasAttribute('required')) {
            console.log('There are some reuired fields!');
            return;
        }
    }

    const fileField = document.getElementById('file');
    const photographerField = document.getElementById('photographer');
    const dateField = document.getElementById('date');
    const airportField = document.getElementById('airport');
    const iataCodeField =  document.getElementById('iata_code');
    const icaoCodeField = document.getElementById('icao_code');
    const countryField = document.getElementById('country');
    const planeField = document.getElementById('plane');
    const regNumberField = document.getElementById('reg_number');
    const serialNumberField = document.getElementById('serial_number');
    const airlineField = document.getElementById('airline');

    try {
        const auth_data = JSON.parse(localStorage.getItem('auth_data'));
        let formData = new FormData();
        formData.append('file', fileField.files[0]);
        formData.append('photographer', photographerField.value);
        formData.append('date', dateField.value);
        formData.append('airport', airportField.value);
        formData.append('iata_code', iataCodeField.value);
        formData.append('icao_code', icaoCodeField.value);
        formData.append('country', countryField.value);
        formData.append('plane', planeField.value);
        formData.append('reg_number', regNumberField.value);
        formData.append('serial_number', serialNumberField.value);
        formData.append('airline', airlineField.value);
        formData.append('username', auth_data.username);

        const reply = await fetch(appUrl.concat('/api/save'), {
            method: 'post',
            headers: {'X-Token': auth_data.token},
            body: formData
        });
        const data = await reply.json();

        console.log(data);

        if(reply.status === 200) {
            deactivateNavbarLinks();
            createPhotoPanel(data);
        }
    }
    catch (e) {
        console.error(e);
    }
}