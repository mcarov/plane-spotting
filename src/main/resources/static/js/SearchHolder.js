import {createCards} from "./App.js";
import {deactivateNavbarLinks} from "./Navbar.js";

const searchUrl = 'http://localhost:8080/api/search/photos?q=';

export async function searchForPhotos(query) {
    try {
        const reply = await fetch(searchUrl.concat(query));
        const data = await reply.json();
        console.log(data);
        deactivateNavbarLinks();
        createCards(data);
    }
    catch (e) {
        console.error(e);
    }
}