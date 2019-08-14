package ru.itpark.planespotting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.planespotting.dto.*;
import ru.itpark.planespotting.model.SavingModel;
import ru.itpark.planespotting.service.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final PhotoService photoService;
    private final PlaneService planeService;
    private final AirlineService airlineService;
    private final AirportService airportService;
    private final ImageService imageService;

    @GetMapping("/photos")
    public List<PhotoDto> getPhotos() {
        return photoService.getPhotos();
    }

    @GetMapping("/photos/{id}")
    public PhotoDto getPhotoById(@PathVariable long id) {
        return photoService.getPhotoById(id);
    }

    @GetMapping("/planes")
    public List<PlaneDto> getPlanes() {
        return planeService.getPlanes();
    }

    @GetMapping("/planes/{name}")
    public List<PhotoDto> getPhotosByPlaneName(@PathVariable String name) {
        return photoService.getPhotosByPlaneName(name);
    }

    @GetMapping("/airlines")
    public List<AirlineDto> getAirlines() {
        return airlineService.getAirlines();
    }

    @GetMapping("/airlines/{name}")
    public List<PhotoDto> getPhotosByAirlineName(@PathVariable String name) {
        return photoService.getPhotosByAirlineName(name);
    }

    @GetMapping("/airports")
    public List<AirportDto> getAirports() {
        return airportService.getAirports();
    }

    @GetMapping("/airports/{name}")
    public List<PhotoDto> getPhotosByAirportName(@PathVariable String name) {
        return photoService.getPhotosByAirportName(name);
    }

    @GetMapping("/search/photos")
    public List<PhotoDto> searchForPlanes(@RequestParam String q) {
        return photoService.searchForPhotos(q);
    }

    @PostMapping(value = "/save")
    public PhotoDto savePhoto(@RequestParam MultipartFile file, @Valid @ModelAttribute SavingModel model) {
        long id = 0;
        if(!file.isEmpty()) {
            String filename = imageService.writeImage(file);
            model.setFilename(filename);
            PhotoDto photoDto = model.getPhotoDtoBuilder().build();
            id = photoService.savePhoto(photoDto);
        }

        return photoService.getPhotoById(id);
    }

    @DeleteMapping("/api/photos/{id}")
    public void removePhotoById(long id) {
        photoService.removePhotoById(id);
    }
}
