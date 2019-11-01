package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.logging.SimpleFormatter;

@Data
public class Publish {
    private Integer id;
    private String name;
    private String userid;
    private String title;
    private String content;
    private String publishUrl;
    private Timestamp createTime;
    private Timestamp updataTime;
}
