package com.example.demo.mapper;

import com.example.demo.model.SignLog;
import com.example.demo.model.SignLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SignLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign_log
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    long countByExample(SignLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign_log
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int deleteByExample(SignLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign_log
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign_log
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int insert(SignLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign_log
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int insertSelective(SignLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign_log
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    List<SignLog> selectByExample(SignLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign_log
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    SignLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign_log
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int updateByExampleSelective(@Param("record") SignLog record, @Param("example") SignLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign_log
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int updateByExample(@Param("record") SignLog record, @Param("example") SignLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign_log
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int updateByPrimaryKeySelective(SignLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sign_log
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int updateByPrimaryKey(SignLog record);
}