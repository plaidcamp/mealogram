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
public class UserAccount extends BaseEntity {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NotNull
    private String userid;

    @Column
    @ManyToOne
    @NotNull
    private UserMaster entnumber;

    @Column(unique = true)
    @Id
    @NotNull
    private String uuid;
    
    @Column
    @NotNull
    private String nickname;

    @Column
    @NotNull
    private String useyn;

    @Column
    @NotNull
    private String birthday;

    @Column
    @NotNull
    private String gender;
}
