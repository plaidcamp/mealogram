package com.plaidcamp.mealogram.apiServer.common.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// 개인 db에서 가져오는 vo. 테스트 후 지울 예정

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class LoginVo {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String priority;

    private String phone;

    @Builder
    public LoginVo(String id, String name, String priority, String phone) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.phone = phone;
    }

    // Entity 클래스는 Setter를 만들지 않는다.
}
