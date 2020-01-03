package com.example.demo.mapper;

import com.example.demo.model.MsgComment;
import com.example.demo.model.MsgCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MsgCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msgcomment
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    long countByExample(MsgCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msgcomment
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int deleteByExample(MsgCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msgcomment
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msgcomment
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int insert(MsgComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msgcomment
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int insertSelective(MsgComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msgcomment
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    List<MsgComment> selectByExample(MsgCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msgcomment
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    MsgComment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msgcomment
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int updateByExampleSelective(@Param("record") MsgComment record, @Param("example") MsgCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msgcomment
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int updateByExample(@Param("record") MsgComment record, @Param("example") MsgCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msgcomment
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int updateByPrimaryKeySelective(MsgComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msgcomment
     *
     * @mbg.generated Thu Jan 02 17:46:13 CST 2020
     */
    int updateByPrimaryKey(MsgComment record);
}