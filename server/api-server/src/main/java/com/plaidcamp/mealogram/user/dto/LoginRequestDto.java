package com.plaidcamp.mealogram.user.dto;

import lombok.*;

@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;
}
