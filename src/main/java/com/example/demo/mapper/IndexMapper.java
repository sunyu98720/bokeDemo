package com.example.demo.mapper;

import com.example.demo.model.MsgComment;
import com.example.demo.model.Publish;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface IndexMapper {
    @Select("<script>" +
            "select * from publish" +
            "<if test='content != null'>" +
            "where 1=1 and content LIKE CONCAT('%',#{content},'%')" +
            "</if>" +
            " limit #{index},#{size}" +
            "</script>")
    List<Publish> selectAllPublish(@Param("index") int index,
                                  @Param("size") int size,
                                  @Param("content") String content);

    @Select("<script>" +
            "select count(1) from publish where 1=1" +
            "<if test='content != null'>" +
            "and content LIKE CONCAT('%',#{content},'%')" +
            "</if>" +
            "</script>")
    Integer count(@Param("content") String content);

    @Select("select * from publish where id = #{id}")
    List<Publish> selectOnePublish(@Param("id") Integer id);

    @Insert("insert into publish (name,userid,title,content,publishUrl,updataTime,createTime) values(#{name},#{userid},#{title},#{content},#{publishUrl},#{updataTime},#{createTime})")
    void createPublish(Publish publish);

    @Insert("insert into msgComment (publishId,userId,name,comment,createTime) values (#{publishId},#{userId},#{name},#{comment},#{createTime})")
    void createComment(MsgComment msgComment);

    @Select("select * from msgComment where publishId = #{publishId}")
    List<MsgComment> findByMsgcomment(@Param("publishId") Integer publishId);

    @Delete("delete from msgComment where publishId = #{publishId} and userId = #{userId}")
    boolean delMsgComment(@Param("publishId")Integer publishId,@Param("userId") String userId);

    @Delete("delete from publish where id = #{id}")
    boolean delMsg(@Param("id") Integer id);
}

