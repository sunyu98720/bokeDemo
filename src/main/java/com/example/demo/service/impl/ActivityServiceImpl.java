package com.example.demo.service.impl;

import com.example.demo.common.HttpResult;
import com.example.demo.common.TimeUtils;
import com.example.demo.common.return_values.Login_RegistCodeMsgEnum;
import com.example.demo.mapper.SignLogMapper;
import com.example.demo.mapper.StudentFormMapper;
import com.example.demo.model.SignLog;
import com.example.demo.model.SignLogExample;
import com.example.demo.model.StudentForm;
import com.example.demo.model.StudentFormExample;
import com.example.demo.service.interfaces.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private StudentFormMapper studentFormMapper;

    @Autowired
    private SignLogMapper signLogMapper;

    private static final Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Override
    public HttpResult signIn(String userId, HttpServletRequest request) {
        StudentFormExample studentFormExample = new StudentFormExample();
        studentFormExample.createCriteria()
                .andTokenEqualTo(userId);
        if(studentFormMapper.selectByExample(studentFormExample) == null){
            logger.info("不能查询到当前用户:" + userId);
            return HttpResult.error(Login_RegistCodeMsgEnum.ERROR_NOTUSER);
        }else{
            TimeUtils timeUtils = new TimeUtils();
            SignLogExample signLogExample = new SignLogExample();
            signLogExample.createCriteria()
                    .andUseridEqualTo(userId)
                    .andCreatetimeGreaterThan(timeUtils.intiDateInt());
            List<SignLog> SignLogs = signLogMapper.selectByExample(signLogExample);
            if(SignLogs == null || SignLogs.isEmpty()){
                StudentForm studentForm = (StudentForm) studentFormMapper.selectByExample(studentFormExample);
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                SignLog signLog = new SignLog();
                signLog.setAccount(studentForm.getAccount());
                signLog.setUserid(studentForm.getToken());
                signLog.setAccountid(studentForm.getId());
                signLog.setAge(studentForm.getAge());
                signLog.setCreatetime(ts);
                signLog.setName(studentForm.getName());
                signLog.setPassword(studentForm.getPassword());
                signLog.setType(1);
                signLogMapper.insert(signLog);
                logger.info("签到成功:" + "userId:" + userId);
                return HttpResult.SUCCESS(Login_RegistCodeMsgEnum.SUCCESS_SIGN);
            }else{
                logger.info("success");
                return HttpResult.error(Login_RegistCodeMsgEnum.ERROR_CHECK_SIGN);
            }
        }
    }
}
