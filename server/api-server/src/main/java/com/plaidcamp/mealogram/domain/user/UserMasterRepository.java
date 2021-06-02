package com.plaidcamp.mealogram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserMasterRepository extends JpaRepository<UserMaster, UUID> {
    Optional<UserMaster> findByEmail(String email);
}
