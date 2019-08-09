import {deactivateNavbarLinks} from "./Navbar.js";
import {photosUrl, imagesUrl} from "./App.js";

export function createPhotoPanel(data) {
    while(root.childElementCount > 0) {
        root.removeChild(root.lastChild);
    }

    const col = document.createElement('div');
    col.className = 'col';
    col.style = 'background-color: #FFFFFF';

    const img = document.createElement('img');
    img.className = 'img-fluid mt-3 mb-3';
    img.src = imagesUrl.concat(`/${data.filename}`);
    col.appendChild(img);

    const text = document.createElement('h5');
    text.className = 'text-center';
    text.innerText = data.plane.name;
    col.appendChild(text);

    const table = document.createElement('table');
    table.className ='table mt-3 mb-3';
    const tbody = document.createElement('tbody');

    let tr = document.createElement('tr');
    let td = document.createElement('td');
    td.innerText = 'Reg number';
    tr.appendChild(td);
    td = document.createElement('td');
    td.innerText = data.plane.regNumber;
    tr.appendChild(td);
    tbody.appendChild(tr);

    tr = document.createElement('tr');
    td = document.createElement('td');
    td.innerText = 'Serial number';
    tr.appendChild(td);
    td = document.createElement('td');
    td.innerText = data.plane.serialNumber;
    tr.appendChild(td);
    tbody.appendChild(tr);

    tr = document.createElement('tr');
    td = document.createElement('td');
    td.innerText = 'Airline';
    tr.appendChild(td);
    td = document.createElement('td');
    td.innerText = data.airline.name;
    tr.appendChild(td);
    tbody.appendChild(tr);

    tr = document.createElement('tr');
    td = document.createElement('td');
    td.innerText = 'Location';
    tr.appendChild(td);
    td = document.createElement('td');

    td.innerText = `${data.airport.name} (${data.airport.iataCode} / ${data.airport.icaoCode}),\n${data.airport.country}`;
    tr.appendChild(td);
    tbody.appendChild(tr);

    tr = document.createElement('tr');
    td = document.createElement('td');
    td.innerText = 'Photographer';
    tr.appendChild(td);
    td = document.createElement('td');
    td.innerText = data.photographer;
    tr.appendChild(td);
    tbody.appendChild(tr);

    tr = document.createElement('tr');
    td = document.createElement('td');
    td.innerText = 'Date';
    tr.appendChild(td);
    td = document.createElement('td');
    td.innerText = data.date;
    tr.appendChild(td);
    tbody.appendChild(tr);

    tr = document.createElement('tr');
    td = document.createElement('td');
    td.innerText = 'Load by user';
    tr.appendChild(td);
    td = document.createElement('td');
    td.innerText = data.username;
    tr.appendChild(td);
    tbody.appendChild(tr);

    table.appendChild(tbody);
    col.appendChild(table);
    root.appendChild(col);
}

export async function getPhoto(id) {
    try {
        const reply = await fetch(photosUrl.concat(`/${id}`));
        const data = await reply.json();
        console.log(data);

        deactivateNavbarLinks();
        createPhotoPanel(data);
    }
    catch (e) {
        console.error(e);
    }
}