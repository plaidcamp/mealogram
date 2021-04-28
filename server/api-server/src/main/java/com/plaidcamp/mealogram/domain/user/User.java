package com.plaidcamp.mealogram.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plaidcamp.mealogram.domain.BaseEntity;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.NonNull;

@Entity
public class User extends BaseEntity {

    @Column
    @NonNull
    private String name;

    @Column(unique = true)
    @NonNull
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @PrePersist
    void hashPassword() {
        if (password == null || password.isEmpty()) {
            throw new DataIntegrityViolationException("Empty password");
        }
        password = new BCryptPasswordEncoder().encode(this.password);
    }

    public boolean comparePassword(String password) {
        return new BCryptPasswordEncoder().matches(password, this.password);
    }

}
