package com.example.demo.Interceptors;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.StudentForm;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Service
public class SessionInterceptorRegistry implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)
            throws Exception {
//      登录预处理,检测登录
        StudentForm studentForm = (StudentForm) request.getSession().getAttribute("studentForm");
        if(studentForm != null){
            return true;
        }
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        JSONObject json = new JSONObject();
        PrintWriter pw = response.getWriter();
        json.put("-1002", "当前用户未登录");
        pw.write(json.toString());
        pw.flush();
        pw.close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)
            throws Exception {
    }


}
