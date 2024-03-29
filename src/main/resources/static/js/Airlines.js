import {appUrl, root, createCards} from "./App.js";
import {deactivateNavbarLinks} from "./Navbar.js";

export async function getAirlines() {
    try {
        const response = await fetch(appUrl.concat('/api/airlines'));
        const data = await response.json();
        console.log(data);

        createTable(data);
    }
    catch (e) {
        console.error(e);
    }
}

async function getPhotosByAirlineName(name) {
    try {
        const response = await fetch(appUrl.concat(`/api/airlines/${name}`));
        const data = await response.json();
        console.log(data);

        createCards(data);
    }
    catch(e) {
        console.error(e);
    }
}

function createTable(array) {
    while(root.childElementCount > 0) {
        root.removeChild(root.lastChild);
    }

    const text = document.createElement('h4');
    text.className = 'text-white text-center';
    text.innerText = 'Airlines';
    root.appendChild(text);

    const table = document.createElement('table');
    table.className = 'table mt-3';
    table.style = 'background-color: #FFFFFF';

    const thead = document.createElement('thead');
    thead.className = 'thead-dark';

    let tr = document.createElement('tr');
    let th = document.createElement('th');
    tr.appendChild(th);
    th = document.createElement('th');
    th.innerText = 'name';
    tr.appendChild(th);
    thead.appendChild(tr);
    table.appendChild(thead);

    const tbody = document.createElement('tbody');
    for(const i of array) {
        tr = document.createElement('tr');
        let td = document.createElement('td');
        td.innerText = `${i.id}`;
        tr.appendChild(td);
        td = document.createElement('td');
        let link = document.createElement('a');
        link.innerText = i.name;
        link.href = "#";
        link.addEventListener('click', function (event) {
            event.preventDefault();
            deactivateNavbarLinks();
            getPhotosByAirlineName(i.name);
        });
        td.appendChild(link);
        tr.appendChild(td);
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);
    root.appendChild(table);
}