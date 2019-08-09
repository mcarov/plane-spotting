package ru.itpark.planespotting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.planespotting.entity.AuthTokenEntity;

public interface AuthTokenRepository extends JpaRepository<AuthTokenEntity, String> {
}
