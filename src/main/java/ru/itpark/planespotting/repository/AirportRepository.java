package ru.itpark.planespotting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itpark.planespotting.entity.AirportEntity;

import java.util.List;

public interface AirportRepository extends JpaRepository<AirportEntity, Long> {
    @Query("SELECT e.id FROM AirportEntity e WHERE lower(e.name) like lower(concat('%', :query,'%'))")
    List<Long> findAllIdsByNameContainingIgnoreCase(@Param("query") String query);

    @Query("SELECT e.id FROM AirportEntity e WHERE lower(e.iataCode) like lower(concat('%', :query,'%'))")
    List<Long> findAllIdsByIataCodeContainingIgnoreCase(@Param("query") String query);

    @Query("SELECT e.id FROM AirportEntity e WHERE lower(e.icaoCode) like lower(concat('%', :query,'%'))")
    List<Long> findAllIdsByIcaoCodeContainingIgnoreCase(@Param("query") String query);

    @Query("SELECT e.id FROM AirportEntity e WHERE lower(e.country) like lower(concat('%', :query,'%'))")
    List<Long> findAllIdsByCountryContainingIgnoreCase(@Param("query") String query);
}
