import {getPhotos} from './App.js';
import {createLoginPanel} from "./Login.js";
import {searchForPhotos} from "./SearchHolder.js";
import {getAirlines} from "./Airlines.js";
import {getPlanes} from "./Planes.js";
import {getAirports} from "./Airports.js";
import {createRegistrationPanel} from "./Registration.js";
import {unauthenticate} from "./AuthHolder.js";
import {createEditPanel} from "./AddPhoto.js";
import {getUserProfile} from "./UserProfile.js";

const navbar = document.getElementById('navbar');
const ul_1 = document.getElementById('links');
const searchField = document.getElementById('search-field');

const li_1 = document.createElement('li');
const li_2 = document.createElement('li');
const li_3 = document.createElement('li');
const li_4 = document.createElement('li');
const li_5 = document.createElement('li');
const li_6 = document.createElement('li');

const ul_2 = document.createElement('ul');

const photosLink = document.createElement('a');
const planesLink = document.createElement('a');
const airlinesLink = document.createElement('a');
const airportsLink = document.createElement('a');
const addPhotoLink = document.createElement('a');
const userLink = document.createElement('a');

const loginFormButton = document.createElement('button');
const signUpFormButton = document.createElement('button');
const logoutButton = document.createElement('button');

function initNavbarElements() {
    searchField.addEventListener('keyup', function(event) {
        if(event.code === 'Enter') {
            searchForPhotos(this.value);
        }
    });

    li_1.className = 'nav-item active';
    photosLink.className = 'nav-link';
    photosLink.id = 'photos-link';
    photosLink.href ="#";
    photosLink.innerText = 'Photos';
    photosLink.addEventListener('click', function (event) {
        event.preventDefault();
        this.parentElement.className = 'nav-item active';
        planesLink.parentElement.className = 'nav-item';
        airlinesLink.parentElement.className = 'nav-item';
        airportsLink.parentElement.className = 'nav-item';
        addPhotoLink.parentElement.className = 'nav-item';
        userLink.parentElement.className = 'nav-item';

        getPhotos();
    });
    li_1.appendChild(photosLink);

    li_2.className = 'nav-item';
    planesLink.className = 'nav-link';
    planesLink.id = 'planes-link';
    planesLink.href ="#";
    planesLink.innerText = 'Planes';
    planesLink.addEventListener('click', function (event) {
        event.preventDefault();
        this.parentElement.className = 'nav-item active';
        photosLink.parentElement.className = 'nav-item';
        airlinesLink.parentElement.className = 'nav-item';
        airportsLink.parentElement.className = 'nav-item';
        addPhotoLink.parentElement.className = 'nav-item';
        userLink.parentElement.className = 'nav-item';

        getPlanes();
    });
    li_2.appendChild(planesLink);

    li_3.className = 'nav-item';
    airlinesLink.className = 'nav-link';
    airlinesLink.id = 'airlines-link';
    airlinesLink.href ="#";
    airlinesLink.innerText = 'Airlines';
    airlinesLink.addEventListener('click', function (event) {
        event.preventDefault();
        this.parentElement.className = 'nav-item active';
        photosLink.parentElement.className = 'nav-item';
        planesLink.parentElement.className = 'nav-item';
        airportsLink.parentElement.className = 'nav-item';
        addPhotoLink.parentElement.className = 'nav-item';
        userLink.parentElement.className = 'nav-item';

        getAirlines();
    });
    li_3.appendChild(airlinesLink);

    li_4.className = 'nav-item';
    airportsLink.className = 'nav-link';
    airportsLink.id = 'airports-link';
    airportsLink.href ="#";
    airportsLink.innerText = 'Airports';
    airportsLink.addEventListener('click', function (event) {
        event.preventDefault();
        this.parentElement.className = 'nav-item active';
        photosLink.parentElement.className = 'nav-item';
        planesLink.parentElement.className = 'nav-item';
        airlinesLink.parentElement.className = 'nav-item';
        addPhotoLink.parentElement.className = 'nav-item';
        userLink.parentElement.className = 'nav-item';

        getAirports();
    });
    li_4.appendChild(airportsLink);

    li_5.className = 'nav-item';
    addPhotoLink.className = 'nav-link';
    addPhotoLink.id = 'add-photo-link';
    addPhotoLink.href ="#";
    addPhotoLink.innerText = 'Add photo';
    addPhotoLink.addEventListener('click', function (event) {
       event.preventDefault();
        this.parentElement.className = 'nav-item active';
        photosLink.parentElement.className = 'nav-item';
        planesLink.parentElement.className = 'nav-item';
        airlinesLink.parentElement.className = 'nav-item';
        airportsLink.parentElement.className = 'nav-item';
        userLink.parentElement.className = 'nav-item';

        createEditPanel();
    });
    li_5.appendChild(addPhotoLink);

    ul_2.className = 'navbar-nav ml-5';
    li_6.className = 'nav-item';
    userLink.className = 'nav-link';
    userLink.id = 'user-link';
    userLink.href ="#";
    userLink.addEventListener('click', function (event) {
        event.preventDefault();
        this.parentElement.className = 'nav-item active';
        photosLink.parentElement.className = 'nav-item';
        planesLink.parentElement.className = 'nav-item';
        airlinesLink.parentElement.className = 'nav-item';
        airportsLink.parentElement.className = 'nav-item';
        addPhotoLink.parentElement.className = 'nav-item';

        getUserProfile();
    });
    li_6.appendChild(userLink);
    ul_2.appendChild(li_6);

    loginFormButton.className = 'btn btn-outline-light ml-5';
    loginFormButton.id = 'login-form-button';
    loginFormButton.innerText = 'Login';
    loginFormButton.addEventListener('click', function () {
        createLoginPanel();
    });

    signUpFormButton.className = 'btn btn-outline-light ml-2';
    signUpFormButton.id = 'reg-form-button';
    signUpFormButton.innerText = 'Sign up';
    signUpFormButton.addEventListener('click', function () {
        createRegistrationPanel();
    });

    logoutButton.className = 'btn btn-outline-light ml-2';
    logoutButton.id = 'logout-button';
    logoutButton.innerText = 'Logout';
    logoutButton.addEventListener('click', function () {
        unauthenticate();
    });
}

export function createNavbar() {
    while(navbar.childElementCount > 2) {
        navbar.removeChild(navbar.lastChild);
    }
    if(localStorage.getItem('auth_data') !== null) {
        const auth_data = JSON.parse(localStorage.getItem('auth_data'));

        ul_1.appendChild(li_1);
        ul_1.appendChild(li_2);
        ul_1.appendChild(li_3);
        ul_1.appendChild(li_4);
        ul_1.appendChild(li_5);

        userLink.innerText = auth_data.username;
        navbar.appendChild(ul_2);
        navbar.appendChild(logoutButton);
    }
    else {
        ul_1.appendChild(li_1);
        ul_1.appendChild(li_2);
        ul_1.appendChild(li_3);
        ul_1.appendChild(li_4);

        navbar.appendChild(loginFormButton);
        navbar.appendChild(signUpFormButton);
    }
}

export function createNavbarForUser() {
    const auth_data = JSON.parse(localStorage.getItem('auth_data'));

    ul_1.appendChild(li_5);

    navbar.removeChild(loginFormButton);
    navbar.removeChild(signUpFormButton);

    userLink.innerText = auth_data.username;
    navbar.appendChild(ul_2);
    navbar.appendChild(logoutButton);
}

export function createDefaultNavbar() {
    ul_1.removeChild(li_5);

    navbar.removeChild(ul_2);
    navbar.removeChild(logoutButton);
    navbar.appendChild(loginFormButton);
    navbar.appendChild(signUpFormButton);
}


export function deactivateNavbarLinks() {
    photosLink.parentElement.className = 'nav-item';
    planesLink.parentElement.className = 'nav-item';
    airlinesLink.parentElement.className = 'nav-item';
    airportsLink.parentElement.className = 'nav-item';
    addPhotoLink.parentElement.className = 'nav-item';
    userLink.parentElement.className = 'nav-item';
}

initNavbarElements();