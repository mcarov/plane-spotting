package ru.itpark.planespotting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.itpark.planespotting.entity.UserEntity;

@Data
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String country;
    private String[] authorities;

    public static UserDto getFrom(UserEntity entity) {
        UserDto userDto = new UserDto();

        userDto.username = entity.getUsername();
        userDto.email = entity.getEmail();
        userDto.firstname = entity.getFirstname();
        userDto.lastname = entity.getLastname();
        userDto.country = entity.getCountry();
        userDto.authorities = entity.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);

        return userDto;
    }
}
