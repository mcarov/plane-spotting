package ru.itpark.planespotting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.planespotting.entity.PhotoEntity;

import java.util.List;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
    List<PhotoEntity> findAllByPlaneId(long id);

    List<PhotoEntity> findAllByAirlineId(long id);

    List<PhotoEntity> findAllByAirportId(long id);
}
