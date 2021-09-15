package com.plaidcamp.mealogram.common.error.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    private HttpStatus status;
    private String errMsg;

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
