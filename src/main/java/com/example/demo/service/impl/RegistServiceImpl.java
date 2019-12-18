package com.example.demo.service.impl;

import com.example.demo.DTO.RegistDTO;
import com.example.demo.common.HttpResult;
import com.example.demo.common.return_values.Login_RegistCodeMsgEnum;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.StudentForm;
import com.example.demo.service.interfaces.RegistService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistServiceImpl implements RegistService {
    @Autowired
    private StudentMapper studentMapper;

//    注册用户
    @Override
    public HttpResult userRegist(RegistDTO RegistDTO){
        try{
            if(RegistDTO.getAccount() == null || RegistDTO.getAccount().equals("") || RegistDTO.getPassword() == null || RegistDTO.getPassword().equals("")){
                return HttpResult.error(Login_RegistCodeMsgEnum.ERROR_NOTNULL.getStatus(), Login_RegistCodeMsgEnum.ERROR_NOTNULL.getMessage());
            }
            if(studentMapper.findByAccount(RegistDTO.getAccount()) != null){
                return HttpResult.SUCCESS(Login_RegistCodeMsgEnum.ERROR_EXIST.getStatus(), Login_RegistCodeMsgEnum.ERROR_EXIST.getMessage());
            }else{
                StudentForm studentForm = new StudentForm();
                RegistDTO.setToken(UUID.randomUUID().toString());
                BeanUtils.copyProperties(RegistDTO,studentForm);
                studentMapper.createUser(studentForm);
            }
            return HttpResult.SUCCESS("注册成功");
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.SYSTEM_ERROR();
        }

    }

//    修改密码
    @Override
    public HttpResult changeUserInfo(RegistDTO registDTO) {
        if(registDTO.getAccount() == null || registDTO.getAccount().equals("")){
            return HttpResult.SUCCESS(Login_RegistCodeMsgEnum.ERROR_USER.getMessage());
        }
        if(registDTO.getPassword() == null || registDTO.getPassword().equals("") || registDTO.getNewPassword() == null || registDTO.getNewPassword().equals("")){
            return HttpResult.SUCCESS(Login_RegistCodeMsgEnum.ERROR_PWD.getMessage());
        }
        StudentForm studentForm = studentMapper.findByAccount(registDTO.getAccount());
        if(null != studentForm){
            if(registDTO.getAccount().equals(studentForm.getAccount()) && registDTO.getPassword().equals(studentForm.getPassword())){
                studentMapper.updateByPassword(registDTO.getNewPassword(), registDTO.getAccount());
                return HttpResult.SUCCESS(Login_RegistCodeMsgEnum.SUCCESS_CHANGGE_PWD.getMessage());

            }else{
                return HttpResult.error(Login_RegistCodeMsgEnum.ERROR_ERRORPWD.getStatus(),Login_RegistCodeMsgEnum.ERROR_ERRORPWD.getMessage());
            }
        }else{
            return HttpResult.error(Login_RegistCodeMsgEnum.ERROR_NOTUSER.getMessage(),Login_RegistCodeMsgEnum.ERROR_NOTUSER.getMessage());
        }
    }
}
