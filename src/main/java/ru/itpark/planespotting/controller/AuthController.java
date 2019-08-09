package ru.itpark.planespotting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itpark.planespotting.dto.AuthTokenRequestDto;
import ru.itpark.planespotting.dto.AuthTokenResponseDto;
import ru.itpark.planespotting.service.AuthService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public AuthTokenResponseDto authenticate(@RequestBody AuthTokenRequestDto dto) {
        return authService.authenticate(dto);
    }
}
