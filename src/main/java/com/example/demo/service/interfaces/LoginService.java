package com.example.demo.service.interfaces;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.common.HttpResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    HttpResult login(LoginDTO loginDTO, HttpServletResponse response,HttpServletRequest request);

}
