import {appUrl, getPhotos} from "./App.js";

export async function register() {
    try {
        const usernameRegField = document.getElementById('username-reg');
        const emailRegField = document.getElementById('email-reg');
        const confirmEmailRegField = document.getElementById('confirm-email-reg');
        const passwordRegField = document.getElementById('password-reg');
        const confirmPasswordRegField = document.getElementById('confirm-password-reg');

        if(emailRegField.value === confirmEmailRegField.value &&
            passwordRegField.value === confirmPasswordRegField.value) {
            const response = await fetch(appUrl.concat('/api/register'), {
                method: 'post',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'username': usernameRegField.value,
                    'email': emailRegField.value,
                    'password': passwordRegField.value
                })
            });
            if(response.status === 201) {
                getPhotos();
                console.log('account created');
            }
            else {
                console.log('account creation failed');
            }

        }
        else {
            if(emailRegField.value !== confirmEmailRegField.value) {
                console.log('Emails do not match');
            }
            if(passwordRegField.value !== confirmPasswordRegField.value) {
                console.log('Passwords do not match');
            }
        }
    }
    catch(e) {
        console.error(e);
    }
}

export async function confirm(token) {
    try {
        const response = await fetch(appUrl.concat('/api/register/confirm'), {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                'token': token
            })
        });
        if(response.status === 204) {
            console.log('verification complete');
        }
        else {
            console.log('verification failed')
        }
    }
    catch(e) {
        console.error(e);
    }
}