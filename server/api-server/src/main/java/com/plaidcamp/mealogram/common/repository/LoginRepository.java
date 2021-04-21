package com.plaidcamp.mealogram.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

import com.plaidcamp.mealogram.common.vo.LoginVo;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<LoginVo, String> { // Entity 클래스와 PK Type

    @Transactional
    Optional<LoginVo> findById(String id);
}
