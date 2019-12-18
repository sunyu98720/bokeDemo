package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentForm implements Serializable {
    private int id;
    private String account;
    private String password;
    private String name;
    private Integer age;
    private String token;
}
