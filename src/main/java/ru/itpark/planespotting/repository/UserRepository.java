package ru.itpark.planespotting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.planespotting.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String query);
}
