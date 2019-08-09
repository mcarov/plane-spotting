package ru.itpark.planespotting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.planespotting.dto.PlaneDto;
import ru.itpark.planespotting.repository.PlaneRepository;


import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlaneService {
    private final PlaneRepository planeRepository;

    public List<PlaneDto> getPlanes() {
        return planeRepository.findAll().stream().map(PlaneDto::getFrom).collect(Collectors.toList());
    }

    public Stream<Long> getPlaneIdsByName(String name) {
        return planeRepository.findAllIdsByNameContainingIgnoreCase(name).stream();
    }

    public Stream<Long> searchForPlaneIds(String query) {
        List<Long> planeIdsByName = planeRepository.findAllIdsByNameContainingIgnoreCase(query);
        List<Long> planeIdsByReg = planeRepository.findAllIdsByRegNumberContainingIgnoreCase(query);
        List<Long> planeIdsBySerial = planeRepository.findAllIdsBySerialNumberContainingIgnoreCase(query);

        return Stream.of(planeIdsByName, planeIdsByReg, planeIdsBySerial)
                .flatMap(Collection::stream)
                .distinct();
    }
}
