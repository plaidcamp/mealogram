package com.plaidcamp.mealogram.common.vo;

public class ReturnMessage {

    private boolean success;

    private Object Data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public static class Builder {
        private boolean success;

        private Object Data;



        public Builder(boolean success) {
            this.success = success;
        }

        public Builder result(Object Data) {
            this.Data = Data;
            return this;
        }

        public ReturnMessage build() {
            ReturnMessage msg = new ReturnMessage();
            msg.setSuccess(this.success);
            msg.setData(this.Data);

            return msg;
        }
    }

}
