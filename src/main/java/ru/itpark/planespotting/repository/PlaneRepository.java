package ru.itpark.planespotting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itpark.planespotting.entity.PlaneEntity;

import java.util.List;

public interface PlaneRepository extends JpaRepository<PlaneEntity, Long> {
    @Query("SELECT e.id FROM PlaneEntity e WHERE lower(e.name) like lower(concat('%', :query,'%'))")
    List<Long> findAllIdsByNameContainingIgnoreCase(@Param("query") String query);

    @Query("SELECT e.id FROM PlaneEntity e WHERE lower(e.regNumber) like lower(concat('%', :query,'%'))")
    List<Long> findAllIdsByRegNumberContainingIgnoreCase(@Param("query") String query);

    @Query("SELECT e.id FROM PlaneEntity e WHERE lower(e.serialNumber) like lower(concat('%', :query,'%'))")
    List<Long> findAllIdsBySerialNumberContainingIgnoreCase(@Param("query") String query);
}
