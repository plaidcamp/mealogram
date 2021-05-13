package com.plaidcamp.mealogram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserMaster, Long> {

}
