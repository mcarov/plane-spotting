package ru.itpark.planespotting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.planespotting.entity.AirlineEntity;

@Data
@NoArgsConstructor
public class AirlineDto {
    private long id;
    private String name;

    public static AirlineDto getFrom(AirlineEntity entity) {
        AirlineDto airlineDto = new AirlineDto();

        airlineDto.id = entity.getId();
        airlineDto.name = entity.getName();

        return airlineDto;
    }
}
