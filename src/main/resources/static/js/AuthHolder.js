import {appUrl, getPhotos} from "./App.js";
import {createNavbarForUser, createDefaultNavbar} from "./Navbar.js";

export async function authenticate() {
    try {
        const usernameField = document.getElementById('username');
        const passwordField = document.getElementById('password');

        const response = await fetch(appUrl.concat('/api/auth'), {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                'username': usernameField.value,
                'password': passwordField.value
            })
        });
        const data = await response.json();

        if(response.status === 200) {
            localStorage.setItem('auth_data', JSON.stringify({
                'token': data.token,
                'username': data.username,
                'authorities': data.authorities
            }));

            createNavbarForUser();
            getPhotos();
        }
        else {
            console.log(data);
        }

        usernameField.value = '';
        passwordField.value = '';

        console.log(JSON.parse(localStorage.getItem('auth_data')));
    }
    catch (e) {
        console.error(e);
    }
}

export function unauthenticate() {
    localStorage.removeItem('auth_data');
    console.log(`auth_data: ${localStorage.getItem('auth_data')}`);

    createDefaultNavbar();
    getPhotos();
}