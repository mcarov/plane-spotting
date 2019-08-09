package ru.itpark.planespotting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.planespotting.entity.RegTokenEntity;

public interface RegTokenRepository extends JpaRepository<RegTokenEntity, String> {
}
