package com.plaidcamp.mealogram.domain.user;

import javax.persistence.*;

import com.plaidcamp.mealogram.domain.BaseEntity;

import com.sun.istack.NotNull;

import java.io.Serializable;

@Entity
public class UserAccount extends BaseEntity implements Serializable {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NotNull
    private String userid;

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

    @ManyToOne
    @JoinColumn(name = "id")
    private UserMaster userMaster;

}
