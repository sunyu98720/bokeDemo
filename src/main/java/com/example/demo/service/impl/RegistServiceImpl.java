package com.example.demo.service.impl;

import com.example.demo.DTO.RegistDTO;
import com.example.demo.common.HttpResult;
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
        if(RegistDTO.getAccount() == null || RegistDTO.getAccount().equals("") || RegistDTO.getPassword() == null || RegistDTO.getPassword().equals("")){
            return HttpResult.error("-1005","用户名密码不能为空");
        }
        if(studentMapper.findByAccount(RegistDTO.getAccount()) != null){
            return HttpResult.SUCCESS("-1001","该用户已存在");
        }else{
            StudentForm studentForm = new StudentForm();
            RegistDTO.setToken(UUID.randomUUID().toString());
            BeanUtils.copyProperties(RegistDTO,studentForm);
            studentMapper.createUser(studentForm);
        }
        return HttpResult.SUCCESS("注册成功");
    }

//    修改密码
    @Override
    public HttpResult changeUserInfo(RegistDTO registDTO) {
        if(registDTO.getAccount() == null || registDTO.getAccount().equals("")){
            return HttpResult.SUCCESS("用户名不能为空");
        }
        if(registDTO.getPassword() == null || registDTO.getPassword().equals("") || registDTO.getNewPassword() == null || registDTO.getNewPassword().equals("")){
            return HttpResult.SUCCESS("密码不能为空");
        }
        StudentForm studentForm = studentMapper.findByAccount(registDTO.getAccount());
        if(null != studentForm){
            if(registDTO.getAccount().equals(studentForm.getAccount()) && registDTO.getPassword().equals(studentForm.getPassword())){
                studentMapper.updateByPassword(registDTO.getNewPassword(), registDTO.getAccount());
                return HttpResult.SUCCESS("修改成功");

            }else{
                return HttpResult.error("-1", "密码不正确");
            }
        }else{
            return HttpResult.error("-2", "用户不存在");
        }
    }
}
