package com.example.demo.Interceptors;

import com.example.demo.common.SignatureInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

/**
*@explain
*Author
*@param
*@return
*@time 2019/11/4 10:04
*/

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private SessionInterceptorRegistry sessionInterceptorRegistry;
//
//    @Autowired
//    private SignatureInterceptor signatureInterceptor;

    @Bean
    public SignatureInterceptor getSignatureInterceptor(){
        return new SignatureInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//       拦截除登录接口外的所有接口请求
        registry.addInterceptor(getSignatureInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }

}
