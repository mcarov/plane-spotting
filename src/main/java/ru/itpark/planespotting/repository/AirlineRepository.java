package ru.itpark.planespotting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itpark.planespotting.entity.AirlineEntity;

import java.util.List;

public interface AirlineRepository extends JpaRepository<AirlineEntity, Long> {
    @Query("SELECT e.id FROM AirlineEntity e WHERE lower(e.name) like lower(concat('%', :query,'%'))")
    List<Long> findAllIdsByNameContainingIgnoreCase(@Param("query") String query);
}
