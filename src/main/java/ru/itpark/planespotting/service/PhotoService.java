package ru.itpark.planespotting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.planespotting.dto.*;
import ru.itpark.planespotting.entity.*;
import ru.itpark.planespotting.exception.PhotoNotFoundException;
import ru.itpark.planespotting.repository.PhotoRepository;
import ru.itpark.planespotting.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final PlaneService planeService;
    private final AirlineService airlineService;
    private final AirportService airportService;

    public List<PhotoDto> getPhotos() {
        return photoRepository.findAll().stream().map(PhotoDto::getFrom).collect(Collectors.toList());
    }

    public List<PhotoDto> getPhotosByPlaneName(String name) {
        return planeService.getPlaneIdsByName(name)
                .map(photoRepository::findAllByPlaneId)
                .flatMap(Collection::stream)
                .map(PhotoDto::getFrom)
                .collect(Collectors.toList());
    }

    public List<PhotoDto> getPhotosByAirlineName(String name) {
        return airlineService.getAirlineIdsByName(name)
                .map(photoRepository::findAllByAirlineId)
                .flatMap(Collection::stream)
                .map(PhotoDto::getFrom)
                .collect(Collectors.toList());
    }

    public List<PhotoDto> getPhotosByAirportName(String name) {
        return airportService.getAirportIdsByName(name)
                .map(photoRepository::findAllByAirportId)
                .flatMap(Collection::stream)
                .map(PhotoDto::getFrom)
                .collect(Collectors.toList());
    }

    public PhotoDto getPhotoById(long id) {
        return PhotoDto.getFrom(photoRepository.findById(id)
                .orElseThrow(() -> new PhotoNotFoundException(String.valueOf(id))));
    }

    public List<PhotoDto> searchForPhotos(String query) {
        Stream<PhotoEntity> streamByPlaneIds = planeService.searchForPlaneIds(query)
                .map(photoRepository::findAllByPlaneId)
                .flatMap(Collection::stream);

        Stream<PhotoEntity> streamByAirlineIds = airlineService.searchForAirlineIds(query)
                .map(photoRepository::findAllByAirlineId)
                .flatMap(Collection::stream);

        Stream<PhotoEntity> streamByAirportIds = airportService.searchForAirportIds(query)
                .map(photoRepository::findAllByAirportId)
                .flatMap(Collection::stream);

        return Stream.of(streamByPlaneIds, streamByAirlineIds, streamByAirportIds)
                .flatMap(s -> s)
                .distinct()
                .map(PhotoDto::getFrom)
                .collect(Collectors.toList());
    }

    public void removePhotoById(long id) {
        photoRepository.deleteById(id);
    }

    public long savePhoto(PhotoDto dto) {
        PhotoEntity photoEntity = getFrom(dto);
        if(dto.getId() != 0) {
            photoRepository.findById(dto.getId())
            .stream().peek(e -> {
                e.setFilename(photoEntity.getFilename());
                e.setPhotographer(photoEntity.getPhotographer());
                e.setDate(photoEntity.getDate());
                e.setPlane(photoEntity.getPlane());
                e.setAirline(photoEntity.getAirline());
                e.setAirport(photoEntity.getAirport());
            })
            .findFirst()
            .orElseThrow(() -> new PhotoNotFoundException(String.valueOf(dto.getId())));
            return photoEntity.getId();
        }

        photoRepository.save(photoEntity);
        photoRepository.flush();

        return photoEntity.getId();
    }

    private PhotoEntity getFrom(PhotoDto dto) {
        PhotoEntity photoEntity = new PhotoEntity();

        photoEntity.setId(dto.getId());
        photoEntity.setFilename(dto.getFilename());
        photoEntity.setPhotographer(dto.getPhotographer());

        try {
            photoEntity.setDate(new SimpleDateFormat("yyy-MM-dd").parse(dto.getDate()));
        }
        catch (ParseException e) {
            photoEntity.setDate(null);
        }

        PlaneDto planeDto = dto.getPlane();
        PlaneEntity planeEntity = new PlaneEntity();
        planeEntity.setId(planeDto.getId());
        planeEntity.setName(planeDto.getName());
        planeEntity.setRegNumber(planeDto.getRegNumber());
        planeEntity.setSerialNumber(planeDto.getSerialNumber());

        AirlineDto airlineDto = dto.getAirline();
        AirlineEntity airlineEntity = new AirlineEntity(airlineDto.getId(), airlineDto.getName());

        AirportDto airportDto = dto.getAirport();
        AirportEntity airportEntity = new AirportEntity();
        airportEntity.setId(airportDto.getId());
        airportEntity.setName(airportDto.getName());
        airportEntity.setIataCode(airportDto.getIataCode());
        airportEntity.setIcaoCode(airportDto.getIcaoCode());
        airportEntity.setCountry(airportDto.getCountry());

        UserEntity userEntity = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(dto.getUsername()));

        photoEntity.setPlane(planeEntity);
        photoEntity.setAirline(airlineEntity);
        photoEntity.setAirport(airportEntity);
        photoEntity.setUser(userEntity);

        return photoEntity;
    }
}
