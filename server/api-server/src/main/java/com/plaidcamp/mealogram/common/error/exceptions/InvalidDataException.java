package com.plaidcamp.mealogram.common.error.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends CustomException {

    public InvalidDataException() {
    }

    public InvalidDataException(String errMsg) {
        this.errMsg = errMsg;
    }

    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private String errMsg;
}
