package com.example.demo.DTO;
import lombok.Data;
@Data
public class MsgCommentDTO {
    /**
     * 姓名
     */
    private String name;

    /**
     * 问题Id
     */
    private Integer publishId;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 评论
     */
    private String comment;

    /**
     * 评论数
     */
    private Integer commentNum;
}
