package com.plaidcamp.mealogram.database.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.hibernate.annotations.GenericGenerator;

import lombok.NonNull;

@Entity
public class User extends BaseTimeEntity {

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    @Id
    private UUID id;

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
