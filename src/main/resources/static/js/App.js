import {getPhoto} from "./Photo.js";
import {createNavbar} from "./Navbar.js";

export const appUrl = 'http://127.0.0.1:8080';
export const photosUrl = appUrl.concat('/api/photos');
export const imagesUrl = appUrl.concat('/api/images');

export const root = document.getElementById('root');

export async function getPhotos() {
    try {
        const reply = await fetch(photosUrl);
        const data = await reply.json();
        console.log(data);

        createCards(data);
    }
    catch (e) {
        console.error(e);
    }
}

export async function createCards(array) {
    while(root.childElementCount > 0) {
        root.removeChild(root.lastChild);
    }

    for(const i of array) {
        let card = document.createElement('div');
        card.className = 'card mb-3';

        let row = document.createElement('div');
        row.className = 'row no-gutters';

        let col_image = document.createElement('div');
        col_image.className = 'col-md-3';

        let col_data = document.createElement('div');
        col_data.className = 'col-md-9';

        let image_link = document.createElement('a');
        image_link.href = '#';
        image_link.addEventListener('click', function (event) {
            event.preventDefault();
            getPhoto(i.id);
        });

        let image = document.createElement('img');
        image.className = 'card-img';
        image.src = imagesUrl.concat(`/${i.filename}`);
        image_link.appendChild(image);

        let cardBody = document.createElement('div');
        cardBody.className = 'card-body';

        let cardTitle = document.createElement('h5');
        cardTitle.className = 'card-title';
        cardTitle.innerText = i.plane.name;
        cardBody.appendChild(cardTitle);

        let cardText = document.createElement('p');
        cardText.className = 'card-text';
        cardText.innerText = `Photographer: ${i.photographer}`;
        cardBody.appendChild(cardText);

        cardText = document.createElement('p');
        cardText.className = 'card-text';
        cardText.innerText = `Date: ${i.date}`;
        cardBody.appendChild(cardText);

        col_image.appendChild(image_link);
        col_data.appendChild(cardBody);
        row.appendChild(col_image);
        row.appendChild(col_data);
        card.appendChild(row);
        root.appendChild(card);
    }
}

createNavbar();
getPhotos();