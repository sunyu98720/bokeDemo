package com.example.demo.controller;

import com.example.demo.DTO.RegistDTO;
import com.example.demo.common.HttpResult;
import com.example.demo.service.interfaces.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/regist")
public class RegistController {
    @Autowired
    private RegistService registService;


    /**
     *@explain 用户注册
     *Author
     *@param registDTO
     *
     *@return com.example.demo.common.HttpResult
     *@time 2019/10/30 18:14
     */
    @RequestMapping(value = "/userRegist",method = RequestMethod.POST)
    public HttpResult userRegist(@RequestBody RegistDTO registDTO){
        return registService.userRegist(registDTO);
    }



    /**
     *@explain 修改密码
     *Author
     *@param registDTO
     *@return com.example.demo.common.HttpResult
     *@time 2019/10/30 18:14
     */
    @RequestMapping(value = "/changeUserInfo",method = RequestMethod.POST)
    public HttpResult changeUserInfo(@RequestBody RegistDTO registDTO){
        return registService.changeUserInfo(registDTO);
    }
}
