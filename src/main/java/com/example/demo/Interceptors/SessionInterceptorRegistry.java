package com.example.demo.Interceptors;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.return_values.Login_RegistCodeMsgEnum;
import com.example.demo.mapper.LoginCheckMapper;
import com.example.demo.model.LoginCheckExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class SessionInterceptorRegistry implements HandlerInterceptor {

    @Autowired
     LoginCheckMapper loginCheckMapper;

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object o)
            throws Exception {


//      登录预处理,检测登录
        LoginCheckExample loginCheckExample = new LoginCheckExample();
        loginCheckExample.createCriteria()
                .andTokenEqualTo(request.getParameter("token"));
        if(loginCheckMapper.countByExample(loginCheckExample) <= 0){
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            JSONObject json = new JSONObject();
            PrintWriter pw = response.getWriter();
            json.put(Login_RegistCodeMsgEnum.ERROR_NOTLOGIN.getStatus(),Login_RegistCodeMsgEnum.ERROR_NOTLOGIN.getMessage());
            pw.write(json.toString());
            pw.flush();
            pw.close();
            return false;
        }else{
            return true;
        }
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
