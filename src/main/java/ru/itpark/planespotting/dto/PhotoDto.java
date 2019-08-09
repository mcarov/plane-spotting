package ru.itpark.planespotting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.planespotting.entity.PhotoEntity;

import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
public class PhotoDto {
    private long id;
    private String filename;
    private String photographer;
    private String date;
    private PlaneDto plane;
    private AirlineDto airline;
    private AirportDto airport;
    private String username;

    public static PhotoDto getFrom(PhotoEntity entity) {
        PhotoDto photoDto = new PhotoDto();
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");

        photoDto.id = entity.getId();
        photoDto.filename = entity.getFilename();
        photoDto.photographer = entity.getPhotographer();

        if(entity.getDate() != null)
            photoDto.date = formatter.format(entity.getDate());
        else
            photoDto.date = "";

        photoDto.plane = PlaneDto.getFrom(entity.getPlane());
        photoDto.airline = AirlineDto.getFrom(entity.getAirline());
        photoDto.airport = AirportDto.getFrom(entity.getAirport());
        photoDto.username = entity.getUser().getUsername();

        return photoDto;
    }
}
