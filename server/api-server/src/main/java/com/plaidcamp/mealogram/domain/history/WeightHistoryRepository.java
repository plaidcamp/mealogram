package com.plaidcamp.mealogram.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WeightHistoryRepository extends JpaRepository<WeightHistory, UUID> {
}
