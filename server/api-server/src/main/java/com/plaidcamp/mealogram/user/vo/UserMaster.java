package com.plaidcamp.mealogram.user.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_MASTER")
public class UserMaster {

    private String ID;

    private int seq;

    private String EMAIL;

    private String password;

    private int account_cnt;

    private int pwerrcnt;

    private String pwinitcode;

    private String phone;

    private String userclass;

    private String facebook_key;

    private String google_key;

    private String kakao_key;

    private LocalDateTime ctime;

    private LocalDateTime utime;
}
