package ru.itpark.planespotting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itpark.planespotting.entity.RegTokenEntity;

public interface RegTokenRepository extends JpaRepository<RegTokenEntity, String> {
    @Query("SELECT COUNT(e) FROM RegTokenEntity e WHERE e.user.id = :id")
    int countRegTokensByUserId(@Param("id") long id);
}
