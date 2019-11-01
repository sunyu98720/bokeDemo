package com.example.demo.mapper;

import com.example.demo.model.StudentForm;
import org.apache.ibatis.annotations.*;


@Mapper
public interface StudentMapper {
    @Select("select * from student_form where account = #{account}")
    StudentForm findByAccount(@Param("account") String account);

    @Insert("insert into student_form(account,password,name,age,token) values(#{account},#{password},#{name},#{age},#{token})")
    void createUser(StudentForm studentForm);

    @Select("select * from student_form where token = #{token}")
    StudentForm findByToken(@Param("token") String token);

    @Update("update student_form set password = #{newPassword} where account = #{account}")
    void updateByPassword(@Param("newPassword") String newPassword,@Param("account") String account);
}
