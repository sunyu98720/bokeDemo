package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MsgComment {
    /*评论ID*/
    private Integer publishId;
    /*评论人ID*/
    private String userId;
    /*名字*/
    private String name;
    /*评论内容*/
    private String comment;
    /*创建时间*/
    private Timestamp createTime;
    /*评论数*/
}
