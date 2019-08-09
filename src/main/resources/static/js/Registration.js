import {deactivateNavbarLinks} from "./Navbar.js";

export function createRegistrationPanel() {
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
    text.innerText = 'Sign up for account';
    card.appendChild(text);

    let form = document.createElement('div');
    form.className = 'form-group';

    const usernameRegLabel = document.createElement('label');
    usernameRegLabel.className = 'text-white';
    usernameRegLabel.for = 'username-reg';
    usernameRegLabel.innerText = 'Username';

    const usernameRegField = document.createElement('input');
    usernameRegField.className = 'form-control';
    usernameRegField.type = 'text';
    usernameRegField.id = 'username-reg';

    form.appendChild(usernameRegLabel);
    form.appendChild(usernameRegField);
    card.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const emailRegLabel = document.createElement('label');
    emailRegLabel.className = 'text-white';
    emailRegLabel.for = 'email-reg';
    emailRegLabel.innerText = 'Email';

    const emailRegField = document.createElement('input');
    emailRegField.className = 'form-control';
    emailRegField.type = 'text';
    emailRegField.id = 'email-reg';

    form.appendChild(emailRegLabel);
    form.appendChild(emailRegField);
    card.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const confirmEmailRegLabel = document.createElement('label');
    confirmEmailRegLabel.className = 'text-white';
    confirmEmailRegLabel.for = 'confirm-email-reg';
    confirmEmailRegLabel.innerText = 'Confirm email';

    const confirmEmailRegField = document.createElement('input');
    confirmEmailRegField.className = 'form-control';
    confirmEmailRegField.type = 'text';
    confirmEmailRegField.id = 'confirm-email-reg';

    form.appendChild(confirmEmailRegLabel);
    form.appendChild(confirmEmailRegField);
    card.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const passwordRegLabel = document.createElement('label');
    passwordRegLabel.className = 'text-white';
    passwordRegLabel.for = 'password-reg';
    passwordRegLabel.innerText = 'Password';

    const passwordRegField = document.createElement('input');
    passwordRegField.className = 'form-control';
    passwordRegField.type = 'password';
    passwordRegField.id = 'password-reg';

    form.appendChild(passwordRegLabel);
    form.appendChild(passwordRegField);
    card.appendChild(form);

    form = document.createElement('div');
    form.className = 'form-group';

    const confirmPasswordRegLabel = document.createElement('label');
    confirmPasswordRegLabel.className = 'text-white';
    confirmPasswordRegLabel.for = 'confirm-password-reg';
    confirmPasswordRegLabel.innerText = 'Confirm password';

    const confirmPasswordRegField = document.createElement('input');
    confirmPasswordRegField.className = 'form-control';
    confirmPasswordRegField.type = 'password';
    confirmPasswordRegField.id = 'confirm-password-reg';

    form.appendChild(confirmPasswordRegLabel);
    form.appendChild(confirmPasswordRegField);
    card.appendChild(form);

    const signUpButton = document.createElement('button');
    signUpButton.className = 'btn btn-outline-light btn-block mt-4';
    signUpButton.innerText = 'Sign up';
    signUpButton.addEventListener('click', function () {

    });

    card.appendChild(signUpButton);
    col.appendChild(card);
    row.appendChild(col);
    root.appendChild(row)
}