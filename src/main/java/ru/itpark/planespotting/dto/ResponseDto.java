package ru.itpark.planespotting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private int status;
    private String reason;
    private String message;
    private ZonedDateTime timestamp;
}
