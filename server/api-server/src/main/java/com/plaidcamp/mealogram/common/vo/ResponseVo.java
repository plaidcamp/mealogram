package com.plaidcamp.mealogram.common.vo;

import org.springframework.http.HttpStatus;

public class ResponseVo {

    private HttpStatus status;
    private Object Data;

    public HttpStatus getStatus() { return status; }
    public void setStatus(HttpStatus status) { this.status = status; }

    public Object getData() {
        return Data;
    }
    public void setData(Object data) {
        Data = data;
    }

    public static class Builder {
        private HttpStatus status;
        private Object Data;

        public Builder result(Object Data) {
            this.Data = Data;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ResponseVo build() {
            ResponseVo msg = new ResponseVo();
            msg.setStatus(status);
            msg.setData(Data);

            return msg;
        }
    }

}
