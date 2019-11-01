package com.example.demo.model;

import lombok.Data;

@Data
public class StudentForm {
    private String account;
    private String password;
    private String name;
    private Integer age;
    private String token;
}
