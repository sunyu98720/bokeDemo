package com.example.demo.common.return_values;


public enum indexEnum implements Code_Enum {
    DELMSG_ERROR("-2001","评论不存在")
    ;

    private String status;
    private String message;

    @Override
    public String getMessage() {
        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setMsg(String msg) {
        this.message = msg;
    }

    indexEnum(String status, String msg){
        this.status = status;
        this.message = msg;
    }
}
