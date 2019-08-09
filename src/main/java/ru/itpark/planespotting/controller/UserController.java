package ru.itpark.planespotting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itpark.planespotting.dto.UserDto;
import ru.itpark.planespotting.entity.UserEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/my-profile")
    public UserDto getUserProfile(@AuthenticationPrincipal UserEntity entity) {
        return UserDto.getFrom(entity);
    }
}
