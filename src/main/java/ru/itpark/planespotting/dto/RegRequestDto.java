package ru.itpark.planespotting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegRequestDto {
    private String username;
    private String email;
    private String password;
}
