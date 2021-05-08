package com.plaidcamp.mealogram.domain.user;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plaidcamp.mealogram.domain.BaseEntity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
public class UserMaster extends BaseEntity {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NotNull
    private Integer entnumber;

    @Column(unique = true)
    @NotNull
    private String email;

    @JsonIgnore
    @Column
    private String password;

    @Column
    private Integer cntAccount;

    @Column
    @NotNull
    private Integer administrate;

    @Column
    private LocalDateTime lastLoginDate;

    @Column
    private Integer pwerrcnt;

    @Column
    private String pwinitcode;

    @Column
    private String phone;

    @Column
    private String userclass;

    @Column
    private String facebookKey;

    @Column
    private String googleKey;



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
