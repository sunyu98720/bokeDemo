package com.example.demo.DTO;

import com.example.demo.model.MsgComment;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PublishDTO {
    private Integer id;
    private String name;
    private String userid;
    private String title;
    private String content;
    private String publishUrl;
    private Timestamp createTime;
    private Timestamp updataTime;
    private List<MsgComment> msgComments;
}
