package com.example.demo.service;

import com.example.demo.mapper.LoginCheckMapper;
import com.example.demo.model.LoginCheckExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */
@Component
public class DeleteLoginStatus {

    @Autowired
    private LoginCheckMapper loginCheckMapper;

    public void deleteStatus(){
        long nowTime = System.currentTimeMillis();
        LoginCheckExample loginCheckExample = new LoginCheckExample();
        loginCheckExample.createCriteria()
                .andLogintimeEqualTo(nowTime);
//        studentMapper.deleteLoginStatus(nowTime);
        loginCheckMapper.selectByExample(loginCheckExample);

    }
}
