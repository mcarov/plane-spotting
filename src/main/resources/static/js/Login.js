import {authenticate} from "./AuthHolder.js";
import {deactivateNavbarLinks} from "./Navbar.js";


export function createLoginPanel() {
    while(root.childElementCount > 0) {
        root.removeChild(root.lastChild);
    }
    deactivateNavbarLinks();

    const row = document.createElement('div');
    row.className = 'row justify-content-center';

    const col = document.createElement('div');
    col.className = 'col-5';

    const card = document.createElement('div');
    card.className = 'card px-5 py-5';
    card.style = 'background-color: #1a5276';

    const text = document.createElement('h5');
    text.className = 'text-center text-white mb-3';
    text.innerText = 'Login to your account';
    card.appendChild(text);

    let form = document.createElement('div');
    form.className = 'form-group';

    const usernameLabel = document.createElement('label');
    usernameLabel.className = 'text-white';
    usernameLabel.for = 'username';
    usernameLabel.innerText = 'Username';

    const usernameField = document.createElement('input');
    usernameField.className = 'form-control';
    usernameField.type = 'text';
    usernameField.id = 'username';

    form.appendChild(usernameLabel);
    form.appendChild(usernameField);
    card.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const passwordLabel = document.createElement('label');
    passwordLabel.className = 'text-white';
    passwordLabel.for = 'password';
    passwordLabel.innerText = 'Password';

    const passwordField = document.createElement('input');
    passwordField.className = 'form-control';
    passwordField.type = 'password';
    passwordField.id = 'password';

    form.appendChild(passwordLabel);
    form.appendChild(passwordField);
    card.appendChild(form);

    const loginButton = document.createElement('button');
    loginButton.className = 'btn btn-outline-light btn-block mt-4';
    loginButton.innerText = 'Login';
    loginButton.addEventListener('click', function () {
        authenticate();
    });

    card.appendChild(loginButton);
    col.appendChild(card);
    row.appendChild(col);
    root.appendChild(row);
}