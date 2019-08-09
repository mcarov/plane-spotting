import {appUrl} from "./App.js";

export async function getUserProfile() {
    try {
        const auth_data = JSON.parse(localStorage.getItem('auth_data'));
        const reply = await fetch(appUrl.concat('/api/users/my-profile'), {
            headers: {'X-Token': auth_data.token}
        });
        const data = await reply.json();
        console.log(data);

        createUserPanel(data);
    }
    catch(e) {
        console.error(e);
    }
}

function createUserPanel(data) {
    while(root.childElementCount > 0) {
        root.removeChild(root.lastChild);
    }

    const row = document.createElement('div');
    row.className = 'row justify-content-center';

    const col = document.createElement('div');
    col.className = 'col-6';

    const card = document.createElement('div');
    card.className = 'card px-3 py-3';
    card.style = 'background-color: #FFFFFF';

    const text = document.createElement('h5');
    text.className = 'text-center';
    text.innerText = data.username;
    card.appendChild(text);

    const table = document.createElement('table');
    table.className ='table mt-3 mb-3';
    const tbody = document.createElement('tbody');

    let tr = document.createElement('tr');
    let td = document.createElement('td');
    td.innerText = 'Email';
    tr.appendChild(td);
    td = document.createElement('td');
    td.innerText = data.email;
    tr.appendChild(td);
    tbody.appendChild(tr);

    tr = document.createElement('tr');
    td = document.createElement('td');
    td.innerText = 'First name';
    tr.appendChild(td);
    td = document.createElement('td');
    td.innerText = data.firstname;
    tr.appendChild(td);
    tbody.appendChild(tr);

    tr = document.createElement('tr');
    td = document.createElement('td');
    td.innerText = 'First name';
    tr.appendChild(td);
    td = document.createElement('td');
    td.innerText = data.lastname;
    tr.appendChild(td);
    tbody.appendChild(tr);

    tr = document.createElement('tr');
    td = document.createElement('td');
    td.innerText = 'Country';
    tr.appendChild(td);
    td = document.createElement('td');
    td.innerText = data.country;
    tr.appendChild(td);
    tbody.appendChild(tr);

    tr = document.createElement('tr');
    td = document.createElement('td');
    td.innerText = 'Status';
    tr.appendChild(td);
    td = document.createElement('td');
    td.innerText = data.authorities;
    tr.appendChild(td);
    tbody.appendChild(tr);

    table.appendChild(tbody);
    card.appendChild(table);
    col.appendChild(card);
    row.appendChild(col);
    root.appendChild(row);
}