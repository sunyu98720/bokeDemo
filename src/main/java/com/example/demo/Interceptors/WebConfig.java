package com.example.demo.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

/**
*@explain
*Author
*@param null
*@return
*@time 2019/11/4 10:04
*/

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptorRegistry sessionInterceptorRegistry;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//       拦截除登录接口外的所有接口请求
        registry.addInterceptor(sessionInterceptorRegistry).addPathPatterns("/**").excludePathPatterns("/login");
    }

}
