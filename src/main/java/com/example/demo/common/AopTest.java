package com.example.demo.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.DTO.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ResponseFacade;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class AopTest {


    @Pointcut("execution(public * com.example.demo.controller.*.*(..)) && @annotation(com.example.demo.common.annotation.MyAnnotation)")
    private void webLog(){

    }

    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    private void checkSign(){

    }

//    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    //请求method前打印内容
    @Before(value = "webLog()")//这个注解的作用是:在切点前执行方法,内容为指定的切点
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //打印请求内容
        log.info("========================请求内容======================");
        log.info("请求地址:" + request.getRequestURI().toString());
        log.info("请求方式" + request.getMethod());
        log.info("请求类方法" + joinPoint.getSignature());
        log.info("请求类方法参数" + Arrays.toString(joinPoint.getArgs()));
        log.info("========================请求内容======================");
    }

    @Before(value = "checkSign()")
    @ResponseBody
    public void checkSign(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String data = JSON.toJSONString(joinPoint.getArgs()[0]);
        System.out.println(data);
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args) {
//            if(arg instanceof LoginDTO){
//                LoginDTO loginDTO = (LoginDTO)arg;
//                String myAccount = loginDTO.getAccount();
//                System.out.println("myAccount:"+myAccount);
//                String myPassword = loginDTO.getPassword();
//                System.out.println("myPassword:"+myPassword);
//                System.out.println(loginDTO);
//            }
//            if(arg instanceof ResponseFacade){
//                ResponseFacade responseFacade = (ResponseFacade)arg;
//                System.out.println(responseFacade);
//            }
//            if(arg instanceof RequestWrapper){
//                RequestWrapper requestWrapper = (RequestWrapper) arg;
//                System.out.println(requestWrapper);
//            }
//        }

        log.info("请求类方法参数" + Arrays.toString(joinPoint.getArgs()));

    }
    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "webLog()")
    //这个注解的作用是:在切入点,return后执行,如果想对某些方法的返回参数进行处理,可以在这操作
    public void methodAfterReturing(Object o) {
        log.info("--------------返回内容----------------");
        log.info("Response内容:12321");
        log.info("--------------返回内容----------------");

    }

}
