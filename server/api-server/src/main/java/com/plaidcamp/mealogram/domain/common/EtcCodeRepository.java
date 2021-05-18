package com.plaidcamp.mealogram.domain.common;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EtcCodeRepository extends JpaRepository<EtcCode, UUID> {
}
