package com.example.demo.service.impl;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.common.HttpResult;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.LoginLog;
import com.example.demo.model.StudentForm;
import com.example.demo.service.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public HttpResult login(LoginDTO loginDTO,
                            HttpServletResponse response,
                            HttpServletRequest request){
        if(loginDTO.getPassword().equals("") || loginDTO.getPassword() == null || loginDTO.getAccount().equals("") || loginDTO.getAccount() == null){
            return HttpResult.SUCCESS("-3", "用户名密码不能为空");
        }
        StudentForm studentForm = studentMapper.findByAccount(loginDTO.getAccount());
        if(studentForm != null){
            if(studentForm.getAccount().equals(loginDTO.getAccount())  && studentForm.getPassword().equals(loginDTO.getPassword())){
                request.getSession().setAttribute("studentForm",studentForm);
//                登录日志
                LoginLog loginLog = new LoginLog();
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                loginLog.setAccountId(studentForm.getId());
                loginLog.setAccount(studentForm.getAccount());
                loginLog.setPassword(studentForm.getPassword());
                loginLog.setUserId(studentForm.getToken());
                loginLog.setName(studentForm.getName());
                loginLog.setAge(studentForm.getAge());
                loginLog.setCreateTime(ts);
                studentMapper.createLoginLog(loginLog);
                return HttpResult.SUCCESS(studentForm);
            }else{
                return HttpResult.SUCCESS("-1", "用户名密码不正确");
            }
        }else{
            return HttpResult.SUCCESS("-2", "用户不存在");
        }
    }
}
