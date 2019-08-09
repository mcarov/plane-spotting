package ru.itpark.planespotting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.planespotting.entity.PlaneEntity;

@Data
@NoArgsConstructor
public class PlaneDto {
    private long id;
    private String name;
    private String regNumber;
    private String serialNumber;

    public static PlaneDto getFrom(PlaneEntity entity) {
        PlaneDto planeDto = new PlaneDto();

        planeDto.id = entity.getId();
        planeDto.name = entity.getName();
        planeDto.regNumber = entity.getRegNumber();
        planeDto.serialNumber = entity.getSerialNumber();

        return planeDto;
    }
}
