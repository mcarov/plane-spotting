package ru.itpark.planespotting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itpark.planespotting.dto.RegConfirmRequestDto;
import ru.itpark.planespotting.dto.RegRequestDto;
import ru.itpark.planespotting.service.RegService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
public class RegController {
    private final RegService regService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegRequestDto dto) {
        regService.register(dto);
    }

    @PostMapping("/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@RequestBody RegConfirmRequestDto dto) {
        regService.confirm(dto);
    }

    @GetMapping("/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void redirect(HttpServletResponse response, @RequestParam String token) throws IOException {
        response.sendRedirect(String.join("", "/?token=", token));
    }
}
