package com.example.demo.service.impl;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.common.HttpResult;
import com.example.demo.common.Login_RegistCodeMsgEnum;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.LoginLog;
import com.example.demo.model.StudentForm;
import com.example.demo.service.interfaces.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private StudentMapper studentMapper;

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Override
    public HttpResult login(LoginDTO loginDTO,
                            HttpServletResponse response,
                            HttpServletRequest request){
        if(loginDTO.getPassword().equals("") || loginDTO.getPassword() == null || loginDTO.getAccount().equals("") || loginDTO.getAccount() == null){
            return HttpResult.SUCCESS(Login_RegistCodeMsgEnum.ERROR_NOTNULL.getStatus(), Login_RegistCodeMsgEnum.ERROR_NOTNULL.getMsg());
        }
        StudentForm studentForm = studentMapper.findByAccount(loginDTO.getAccount());
        if(studentForm != null){

            if(studentForm.getAccount().equals(loginDTO.getAccount())  && studentForm.getPassword().equals(loginDTO.getPassword())){
                try{
                    request.getSession().setAttribute("studentForm",studentForm);
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

                    logger.info("登录成功:" + studentForm);
                    return HttpResult.SUCCESS(studentForm);
                }catch (Exception e){
                    e.printStackTrace();
                    return HttpResult.SYSTEM_ERROR();
                }

            }else{
                logger.info("用户名密码不正确," + "用户名:" + loginDTO.getAccount() + "," + "密码:" + loginDTO.getPassword());
                return HttpResult.SUCCESS(Login_RegistCodeMsgEnum.ERROR.getStatus(), Login_RegistCodeMsgEnum.ERROR.getMsg());
            }
        }else{
            logger.info("用户不存在," + "用户名:" + loginDTO.getAccount());
            return HttpResult.SUCCESS(Login_RegistCodeMsgEnum.ERROR_NOTUSER.getStatus(), Login_RegistCodeMsgEnum.ERROR_NOTUSER.getMsg());
        }
    }
}
