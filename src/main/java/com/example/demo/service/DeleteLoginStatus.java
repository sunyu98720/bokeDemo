package com.example.demo.service;

import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */
@Service
public class DeleteLoginStatus {

    @Autowired
    private StudentMapper studentMapper;

    public void deleteStatus(){
        long nowTime = System.currentTimeMillis();
        studentMapper.deleteLoginStatus(nowTime);


    }
}
