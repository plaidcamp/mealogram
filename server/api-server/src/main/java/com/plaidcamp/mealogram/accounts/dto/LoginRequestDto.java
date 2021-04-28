package com.plaidcamp.mealogram.accounts.dto;

import lombok.*;

@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;
}
