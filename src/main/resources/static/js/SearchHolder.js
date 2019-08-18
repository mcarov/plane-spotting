import {appUrl, createCards} from "./App.js";
import {deactivateNavbarLinks} from "./Navbar.js";

export async function searchForPhotos(query) {
    try {
        const response = await fetch(appUrl.concat(`/api/search/photos?q=${query}`));
        const data = await response.json();
        console.log(data);
        deactivateNavbarLinks();
        createCards(data);
    }
    catch (e) {
        console.error(e);
    }
}