package com.example.demo.controller;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.common.HttpResult;
import com.example.demo.service.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    /**
    *@explain 登录
    *Author
    *@param loginDTO
     * account
     * password
    *@param response
    *@param request
    *@return com.example.demo.common.HttpResult
    *@time 2019/10/30 18:14
    */
    public HttpResult login(@RequestBody LoginDTO loginDTO, HttpServletResponse response, HttpServletRequest request){
        return loginService.login(loginDTO,response,request);

    }
}
