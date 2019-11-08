package com.example.demo.service.impl;

import com.example.demo.common.HttpResult;
import com.example.demo.common.TimeUtils;
import com.example.demo.mapper.ActivityMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.SignLog;
import com.example.demo.model.StudentForm;
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
    private ActivityMapper activityMapper;

    @Autowired
    private StudentMapper studentMapper;

    private static final Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Override
    public HttpResult sign(String userId, HttpServletRequest request) {
        if(userId == null || userId.equals("")){
            logger.info("userId为空");
            return HttpResult.error("-5","userId不能为空");
        }
        if(studentMapper.findByToken(userId) == null){
            logger.info("不能查询到当前用户:" + userId);
            return HttpResult.error("-1","用户不存在");
        }else{
            TimeUtils timeUtils = new TimeUtils();
            List<SignLog> SignLogs = activityMapper.findSignLogs(userId,timeUtils.initDateInt());
            if(SignLogs == null || SignLogs.isEmpty()){
                StudentForm studentForm = (StudentForm) request.getSession().getAttribute("studentForm");
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                SignLog signLog = new SignLog();
                signLog.setAccount(studentForm.getAccount());
                signLog.setUserId(studentForm.getToken());
                signLog.setAccountId(studentForm.getId());
                signLog.setAge(studentForm.getAge());
                signLog.setCreateTime(ts);
                signLog.setName(studentForm.getName());
                signLog.setPassword(studentForm.getPassword());
                signLog.setType(1);
                activityMapper.createSignLog(signLog);
                logger.info("签到成功:" + "userId:" + userId);
                return HttpResult.SUCCESS("102","签到成功");
            }else{
                logger.info("success");
                return HttpResult.SUCCESS("已签到");
            }
        }
    }
}
