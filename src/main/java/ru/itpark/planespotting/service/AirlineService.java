package ru.itpark.planespotting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.planespotting.dto.AirlineDto;
import ru.itpark.planespotting.repository.AirlineRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AirlineService {
    private final AirlineRepository airlineRepository;

    public List<AirlineDto> getAirlines() {
        return airlineRepository.findAll().stream().map(AirlineDto::getFrom).collect(Collectors.toList());
    }

    public Stream<Long> getAirlineIdsByName(String name) {
        return airlineRepository.findAllIdsByNameContainingIgnoreCase(name).stream();
    }

    public Stream<Long> searchForAirlineIds(String query) {
        return airlineRepository
                .findAllIdsByNameContainingIgnoreCase(query)
                .stream();
    }
}
