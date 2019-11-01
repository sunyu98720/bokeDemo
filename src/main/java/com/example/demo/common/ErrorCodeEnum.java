package com.example.demo.common;

public enum ErrorCodeEnum {
    SUCCESS("200","请求成功"),
    ERROR("-200","请求失败"),
    SYSTEM_ERROR("-500","系统异常"),
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
