package ru.itpark.planespotting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itpark.planespotting.dto.RegConfirmRequestDto;
import ru.itpark.planespotting.dto.RegRequestDto;
import ru.itpark.planespotting.service.RegService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
public class RegController {
    private final RegService regService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@RequestBody RegRequestDto dto) {
        regService.register(dto);
    }

    @PostMapping("/confirm")
    public void confirm(@RequestBody RegConfirmRequestDto dto) {
        regService.confirm(dto);
    }
}
