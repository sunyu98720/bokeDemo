package com.example.demo.service.impl;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.common.HttpResult;
import com.example.demo.common.return_values.ErrorCodeEnum;
import com.example.demo.common.return_values.Login_RegistCodeMsgEnum;
import com.example.demo.common.config.RedisUtil;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.LoginLog;
import com.example.demo.model.StudentForm;
import com.example.demo.service.interfaces.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

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
    private StudentMapper studentMapper;

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public HttpResult login(LoginDTO loginDTO,
                            HttpServletResponse response,
                            HttpServletRequest request){
//        if(loginDTO.getPassword().equals("") || loginDTO.getPassword() == null || loginDTO.getAccount().equals("") || loginDTO.getAccount() == null){
//            return HttpResult.SUCCESS(Login_RegistCodeMsgEnum.ERROR_NOTNULL.getStatus(), Login_RegistCodeMsgEnum.ERROR_NOTNULL.getMsg());
//        }
        StudentForm studentForm = studentMapper.findByAccount(loginDTO.getAccount());
        if(studentForm != null){

            if(studentForm.getAccount().equals(loginDTO.getAccount())  && studentForm.getPassword().equals(loginDTO.getPassword())){
//                try{
//                    request.getSession().setAttribute("token",studentForm.getToken());
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    studentMapper.loginCheck(studentForm.getToken(),System.currentTimeMillis() + 1800000);
                    LoginLog loginLog = new LoginLog();
                    loginLog.setAccountId(studentForm.getId());
                    loginLog.setAccount(studentForm.getAccount());
                    loginLog.setPassword(studentForm.getPassword());
                    loginLog.setUserId(studentForm.getToken());
                    loginLog.setName(studentForm.getName());
                    loginLog.setAge(studentForm.getAge());
                    loginLog.setCreateTime(ts);
                    Jedis jedis = new Jedis();
                    jedis.select(2);
//                    jedis.append(studentForm.getToken(),loginLog.toString());
//                    redisUtil.set(studentForm.getToken(), loginLog,1000,2);
                    jedis.set(studentForm.getToken(),loginLog.toString());
                    studentMapper.createLoginLog(loginLog);

                    logger.info("登录成功:" + studentForm);
                    return HttpResult.SUCCESS(studentForm);
//                }catch (Exception e){
//                    e.printStackTrace();
//                    return HttpResult.SYSTEM_ERROR();
//                }

            }else{
                logger.info("用户名密码不正确," + "用户名:" + loginDTO.getAccount() + "," + "密码:" + loginDTO.getPassword());
//                return HttpResult.SUCCESS(Login_RegistCodeMsgEnum.ERROR.getStatus(), Login_RegistCodeMsgEnum.ERROR.getMessage());
                return HttpResult.error(Login_RegistCodeMsgEnum.ERROR);
            }


        }else{
            logger.info("用户不存在," + "用户名:" + loginDTO.getAccount());
//            return HttpResult.SUCCESS(Login_RegistCodeMsgEnum.ERROR_NOTUSER.getStatus(), Login_RegistCodeMsgEnum.ERROR_NOTUSER.getMsg());
//            return HttpResult.error(ErrorCodeEnum.ERROR);
            return HttpResult.error(Login_RegistCodeMsgEnum.ERROR_NOTUSER);
        }

    }
}
