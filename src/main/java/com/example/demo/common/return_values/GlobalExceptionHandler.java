package com.example.demo.common.return_values;

import com.example.demo.common.HttpResult;
import lombok.extern.log4j.Log4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * Author
 *全局异常处理
 * @param
 * @return
 * @time
 */
@RestControllerAdvice
@Log4j
public class GlobalExceptionHandler {
    /**
    *@explain 参数校验异常捕获
    *Author
    *@param null
    *@return
    *@time 2019/12/18 11:46
    */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info(e.getMessage(),e);
        return HttpResult.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
    *@explain 参数校验异常捕获
    *Author
    *@param null
    *@return
    *@time 2019/12/18 11:46
    */
    @ExceptionHandler(ConstraintViolationException.class)
    public HttpResult handleConstraintViolationException(ConstraintViolationException e) {
        log.info(e.getMessage(),e);
//        return HttpResult.error(e.getBindingResult().getFieldError().getDefaultMessage());
        return HttpResult.error(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public HttpResult handleException(Exception e) {
        log.info(e.getMessage());
        e.printStackTrace();
        return HttpResult.error(ErrorCodeEnum.SYSTEM_ERROR);
    }
    /**
    *@explain 捕获空指针异常
    *Author
    *@param null
    *@return
    *@time 2019/12/18 11:45
    */
    @ExceptionHandler(NullPointerException.class)
    public HttpResult handleNullPointerException(NullPointerException e){
        e.printStackTrace();
        return HttpResult.error(ErrorCodeEnum.SYSTEM_ERROR);
    }
}
