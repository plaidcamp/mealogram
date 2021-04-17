package com.plaidcamp.mealogram.apiServer.mealogram.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="MEALOGRAM_PLATE")
public class MealogramPlate {

    @Id
    private String post_id;

    @Id
    private String plate_id;

    @Id
    private String user_id;
}
