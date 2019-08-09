package ru.itpark.planespotting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.planespotting.entity.AirportEntity;

@Data
@NoArgsConstructor
public class AirportDto {
    private long id;
    private String name;
    private String iataCode;
    private String icaoCode;
    private String country;

    public static AirportDto getFrom(AirportEntity entity) {
        AirportDto airportDto = new AirportDto();

        airportDto.id = entity.getId();
        airportDto.name = entity.getName();
        airportDto.iataCode = entity.getIataCode();
        airportDto.icaoCode = entity.getIcaoCode();
        airportDto.country = entity.getCountry();

        return airportDto;
    }
}
