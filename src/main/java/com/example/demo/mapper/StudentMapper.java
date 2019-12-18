package com.example.demo.mapper;

import com.example.demo.model.LoginLog;
import com.example.demo.model.StudentForm;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


public interface StudentMapper {
    @Select("select * from student_form where account = #{account}")
    StudentForm findByAccount(@Param("account") String account);

    @Insert("insert into student_form(account,password,name,age,token) values(#{account},#{password},#{name},#{age},#{token})")
    void createUser(StudentForm studentForm);

    @Select("select * from student_form where token = #{token}")
    StudentForm findByToken(@Param("token") String token);

    @Update("update student_form set password = #{newPassword} where account = #{account}")
    void updateByPassword(@Param("newPassword") String newPassword,@Param("account") String account);

    @Insert("INSERT INTO login_log (accountId,userId,account, password,name,age,createTime) VALUES (#{accountId},#{userId}, #{account}, #{password}, #{name}, #{age}, #{createTime})")
    void createLoginLog(LoginLog loginLog);

    @Insert(("INSERT INTO loginCheck (token,loginTime) VALUES (#{token},#{loginTime})"))
    void loginCheck(@Param("token") String token, @Param("loginTime")long loginTime);

    @Delete("Delete from loginCheck where #{nowTime} > loginTime")
    void deleteLoginStatus(@Param("nowTime")long nowTime);

    @Select("select count(1) from loginCheck where token = #{token}")
    int selectToken(@Param("token") String token);

}
