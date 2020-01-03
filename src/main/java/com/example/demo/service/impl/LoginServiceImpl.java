package com.example.demo.service.impl;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.common.HttpResult;
import com.example.demo.common.return_values.Login_RegistCodeMsgEnum;
import com.example.demo.common.config.RedisUtil;
import com.example.demo.mapper.LoginCheckMapper;
import com.example.demo.mapper.LoginLogMapper;
import com.example.demo.mapper.StudentFormMapper;
import com.example.demo.model.LoginCheck;
import com.example.demo.model.LoginLog;
import com.example.demo.model.StudentForm;
import com.example.demo.model.StudentFormExample;
import com.example.demo.service.interfaces.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@Service
public class LoginServiceImpl implements LoginService {
    private static int ExpireTime = 60;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private StudentFormMapper studentFormMapper;

    @Autowired
     LoginCheckMapper loginCheckMapper;

    @Autowired
    private LoginLogMapper loginLogMapper;

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public HttpResult login(LoginDTO loginDTO,
                            HttpServletResponse response,
                            HttpServletRequest request){

        StudentFormExample studentFormExample = new StudentFormExample();
        studentFormExample.createCriteria()
                .andAccountEqualTo(loginDTO.getAccount());
        StudentForm studentForm = studentFormMapper.selectByOneExample(studentFormExample);
        if(studentForm != null){
            if(studentForm.getAccount().equals(loginDTO.getAccount())  && studentForm.getPassword().equals(loginDTO.getPassword())){
                try{
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    LoginCheck loginCheck = new LoginCheck();
                    loginCheck.setToken(studentForm.getToken());
                    loginCheck.setLogintime(System.currentTimeMillis() + 1800000);
                    loginCheckMapper.insert(loginCheck);
                    LoginLog loginLog = new LoginLog();
                    loginLog.setAccountid(studentForm.getId());
                    loginLog.setAccount(studentForm.getAccount());
                    loginLog.setPassword(studentForm.getPassword());
                    loginLog.setUserid(studentForm.getToken());
                    loginLog.setName(studentForm.getName());
                    loginLog.setAge(studentForm.getAge());
                    loginLog.setCreatetime(ts);
//                    Jedis jedis = new Jedis();
//                    jedis.select(2);
//                    jedis.append(studentForm.getToken(),loginLog.toString());
//                    redisUtil.set(studentForm.getToken(), loginLog,1000,2);
//                    jedis.set(studentForm.getToken(),loginLog.toString());
                    loginLogMapper.insert(loginLog);

                    logger.info("登录成功:" + studentForm);
                    return HttpResult.SUCCESS(studentForm);
                }catch (Exception e){
                    e.printStackTrace();
                    return HttpResult.SYSTEM_ERROR();
                }

            }else{
                logger.info("用户名密码不正确," + "用户名:" + loginDTO.getAccount() + "," + "密码:" + loginDTO.getPassword());
                return HttpResult.error(Login_RegistCodeMsgEnum.ERROR);
            }


        }else{
            logger.info("用户不存在," + "用户名:" + loginDTO.getAccount());
            return HttpResult.error(Login_RegistCodeMsgEnum.ERROR_NOTUSER);
        }

    }
}
