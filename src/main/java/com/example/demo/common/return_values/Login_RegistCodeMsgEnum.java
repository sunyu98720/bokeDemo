package com.example.demo.common.return_values;

public enum Login_RegistCodeMsgEnum implements Code_Enum {
    SUCCESS("1001","登录成功"),
    SUCCESS_SIGN("1002","签到成功"),
    SUCCESS_CHANGGE_PWD("1002","密码修改成功"),
    ERROR_USER("-1001","用户名不能为空"),
    ERROR_PWD("-1002","密码不能为空"),
    ERROR("-1003","用户名密码错误"),
    ERROR_NOTUSER("-1004","用户不存在"),
    ERROR_NOTNULL("-1005","用户名密码不能为空"),
    ERROR_EXIST("-1006","用户已存在"),
    ERROR_ERRORPWD("-1007","密码不正确"),
    ERROR_NOTLOGIN("-1008","用户未登录"),
    ERROR_CHECK_SIGN("-1009","已签到"),
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

    public void setMsg(String msg) {
        this.message = message;
    }

    Login_RegistCodeMsgEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
