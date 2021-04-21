package com.plaidcamp.mealogram.database.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

}
