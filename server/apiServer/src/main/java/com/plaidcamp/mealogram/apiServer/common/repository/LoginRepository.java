package com.plaidcamp.mealogram.apiServer.common.repository;

import com.plaidcamp.mealogram.apiServer.common.vo.LoginVo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<LoginVo, String> { // Entity 클래스와 PK Type

    @Transactional
    Optional<LoginVo> findById(String id);
}
