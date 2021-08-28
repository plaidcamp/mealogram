package com.plaidcamp.mealogram.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationDto {
    private String email;
    private String password;
    private String phone;

    @Override
    public String toString() {
        return "RegistrationDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
