package com.example.demo.service.interfaces;

import com.example.demo.common.HttpResult;

import javax.servlet.http.HttpServletRequest;

public interface ActivityService {
    HttpResult sign(String userId, HttpServletRequest request);
}
