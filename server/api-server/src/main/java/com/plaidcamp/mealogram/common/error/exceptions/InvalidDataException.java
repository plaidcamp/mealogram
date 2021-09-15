package com.plaidcamp.mealogram.common.error.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends CustomException {

    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public InvalidDataException() {
    }

    public InvalidDataException(String errMsg) {
        this.errMsg = errMsg;
        printException();
    }

    private String errMsg;

    public void printException() {
        System.out.println("Status : " + this.status);
        System.out.println("Msg : " + this.errMsg);
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }
}
