package com.example.demo.model;

import lombok.Data;

@Data
public class StudentForm {
    private int id;
    private String account;
    private String password;
    private String name;
    private Integer age;
    private String token;
}
