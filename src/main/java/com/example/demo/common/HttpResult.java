package com.example.demo.common;


import com.example.demo.common.return_values.ErrorCodeEnum;
import com.example.demo.common.return_values.Code_Enum;

import java.io.Serializable;

public class HttpResult<T> implements Serializable {
    private String code;
    private String msg;
    private T result;

    private static final String OK_CODE = ErrorCodeEnum.SUCCESS.getStatus();
    private static final String OK_MESSAGE = ErrorCodeEnum.SUCCESS.getMessage();
    private  static final String ERROR_CODE = ErrorCodeEnum.SYSTEM_ERROR.getStatus();
    private  static final String ERROR_MESSAGE = ErrorCodeEnum.SYSTEM_ERROR.getMessage();

    /**
     * 成功返回方法
     * @param result
     * @param <T>
     * @return
     */


    public static <T> HttpResult<T> SUCCESS(T result){
        HttpResult<T> httpResult = new HttpResult<>();
        httpResult.setCode(OK_CODE);
        httpResult.setMsg(OK_MESSAGE);
        httpResult.setResult(result);
        return  httpResult;
    }

    public static <T> HttpResult<T> SUCCESS(Code_Enum codeEnum){
        HttpResult<T> httpResult = new HttpResult<>();
        httpResult.setCode(codeEnum.getStatus());
        httpResult.setMsg(codeEnum.getMessage());
        return  httpResult;
    }


    public static <T>HttpResult<T> SUCCESS(String code,String msg){
        HttpResult<T> httpResult = new HttpResult<>();
        httpResult.setCode(code);
        httpResult.setMsg(msg);
        return httpResult;
    }

    public static <T>HttpResult<T> SUCCESS(String msg){
        HttpResult<T> httpResult = new HttpResult<>();
        httpResult.setCode(OK_CODE);
        httpResult.setMsg(msg);
        return httpResult;
    }

    public static <T>HttpResult<T> SUCCESS(){
        HttpResult<T> httpResult = new HttpResult<>();
        httpResult.setCode(OK_CODE);
        httpResult.setMsg(OK_MESSAGE);
        return httpResult;
    }

    /**
     * 失败方法
     * @return
     */
    public static HttpResult error(String code, String message){
        HttpResult result = new HttpResult();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }
    public static <T> HttpResult<T> error(Code_Enum errorCodeEnum){
        HttpResult result = new HttpResult();
        result.setCode(errorCodeEnum.getStatus());
        result.setMsg(errorCodeEnum.getMessage());
        return result;
    }

    /**
     *@explain固定code异常处理
     *Author
     *@param message
     *@return com.example.demo.common.HttpResult
     *@time 2019/12/13 11:18
     */
    public static HttpResult error(String message){

        HttpResult result = new HttpResult();
        result.setCode("-9999");
        result.setMsg(message);
        return result;
    }

    /**
     * 系统异常
     * @return
     */
    public static HttpResult SYSTEM_ERROR(){
        HttpResult result = new HttpResult();
        result.setCode(ERROR_CODE);
        result.setMsg(ERROR_MESSAGE);
        return result;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
