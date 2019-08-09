package ru.itpark.planespotting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.planespotting.dto.AirportDto;
import ru.itpark.planespotting.repository.AirportRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    public List<AirportDto> getAirports() {
        return airportRepository.findAll().stream().map(AirportDto::getFrom).collect(Collectors.toList());
    }

    public Stream<Long> getAirportIdsByName(String name) {
        return airportRepository.findAllIdsByNameContainingIgnoreCase(name).stream();
    }

    public Stream<Long> searchForAirportIds(String query) {
        List<Long> airportIdsByName = airportRepository.findAllIdsByNameContainingIgnoreCase(query);
        List<Long> airportIdsByIataCode = airportRepository.findAllIdsByIataCodeContainingIgnoreCase(query);
        List<Long> airportIdsByIcaoCode = airportRepository.findAllIdsByIcaoCodeContainingIgnoreCase(query);
        List<Long> airportIdsByCountry = airportRepository.findAllIdsByCountryContainingIgnoreCase(query);

        return Stream.of(airportIdsByName, airportIdsByIataCode, airportIdsByIcaoCode, airportIdsByCountry)
                .flatMap(Collection::stream)
                .distinct();
    }
}
