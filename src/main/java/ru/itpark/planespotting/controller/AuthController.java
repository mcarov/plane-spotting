package ru.itpark.planespotting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itpark.planespotting.dto.AuthRequestDto;
import ru.itpark.planespotting.dto.AuthResponseDto;
import ru.itpark.planespotting.service.AuthService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public AuthResponseDto authenticate(@RequestBody AuthRequestDto dto) {
        return authService.authenticate(dto);
    }
}
