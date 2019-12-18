package com.example.demo.mapper;

import com.example.demo.model.LoginLog;
import com.example.demo.model.SignLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */


public interface ActivityMapper {
    @Insert("INSERT INTO sign_log (id, accountId, userId, password, name, age, createTime, type) VALUES (#{id}, #{accountId}, #{userId}, #{password}, #{name}, #{age}, #{createTime}, #{type})")
    void createSignLog(SignLog signLog);

    @Select("select * from sign_log where createTime > #{initToday} and userId = #{userId}")
    List<SignLog> findSignLogs(@Param("userId") String userId,
                                @Param("initToday") String initToday);

}
