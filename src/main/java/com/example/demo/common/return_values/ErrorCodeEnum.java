package com.example.demo.common.return_values;

public enum ErrorCodeEnum implements Code_Enum {
    SUCCESS("200","请求成功"),
    ERROR("-200","请求失败"),
    SYSTEM_ERROR("-500","系统异常,请稍后重试!"),
    ;

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    ErrorCodeEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
