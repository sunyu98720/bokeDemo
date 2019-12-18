package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Author
 *
 * @param
 * @return
 * @time
 */

@Data
public class LoginLog implements Serializable {
    private int id;
    private int accountId;
    private String userId;
    private String account;
    private String password;
    private String name;
    private int age;
    private Timestamp createTime;
}
