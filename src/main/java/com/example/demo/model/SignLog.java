package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */
@Data
public class SignLog {
    private int id;
    private int accountId;
    private String userId;
    private String account;
    private String password;
    private String name;
    private int age;
    private Timestamp createTime;
    private int type;
}
