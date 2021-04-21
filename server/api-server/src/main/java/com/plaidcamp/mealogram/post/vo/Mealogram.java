package com.plaidcamp.mealogram.post.vo;

import lombok.Data;

import javax.persistence.*;

/**
 * No VO : Table VO
 */

@Data
@Entity
@Table(name = "MEALOGRAM")
public class Mealogram {

    @Id
    @Column(nullable = false)
    private String post_id;

    @Id
    @Column(nullable = false)
    private String user_id;

    @Column(nullable = false)
    private String write_time;

    private String eatting_time;

    private int fullness;

    private int satisfation;

    @Lob
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private String ctime;

    @Temporal(TemporalType.TIMESTAMP)
    private String utime;

}
