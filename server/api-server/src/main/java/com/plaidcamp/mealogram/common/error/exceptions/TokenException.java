package com.plaidcamp.mealogram.common.error.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TokenException extends Exception {

    private String msg;
    private HttpStatus status;

    public TokenException(String msg) {
        this.status = HttpStatus.BAD_REQUEST;
        this.msg = msg;
    }
}
