package ru.itpark.planespotting.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.planespotting.dto.*;
import ru.itpark.planespotting.validation.AirportCode;

@Data
@NoArgsConstructor
public class SavingModel {
    private String filename;
    private String photographer;
    private String date;
    private String airport;
    @AirportCode(type = AirportCode.Type.IATA, message = "{api.validation.iata-code}")
    private String iata_code;
    @AirportCode(type = AirportCode.Type.ICAO, message = "{api.validation.icao-code}")
    private String icao_code;
    private String country;
    private String plane;
    private String reg_number;
    private String serial_number;
    private String airline;
    private String username;

    public PhotoDtoBuilder getPhotoDtoBuilder() {
        return new PhotoDtoBuilder();
    }

    public class PhotoDtoBuilder {
        private PhotoDto photoDto;

        private PhotoDtoBuilder() {
            photoDto = new PhotoDto();
        }

        public PhotoDto build() {
            photoDto.setFilename(filename);
            photoDto.setPhotographer(photographer);
            photoDto.setDate(date);

            PlaneDto planeDto = new PlaneDto();
            planeDto.setName(plane);
            planeDto.setRegNumber(reg_number);
            planeDto.setSerialNumber(serial_number);

            AirlineDto airlineDto = new AirlineDto();
            airlineDto.setName(airline);

            AirportDto airportDto = new AirportDto();
            airportDto.setName(airport);
            airportDto.setIataCode(iata_code);
            airportDto.setIcaoCode(icao_code);
            airportDto.setCountry(country);

            photoDto.setPlane(planeDto);
            photoDto.setAirline(airlineDto);
            photoDto.setAirport(airportDto);
            photoDto.setUsername(username);

            return photoDto;
        }
    }
}
