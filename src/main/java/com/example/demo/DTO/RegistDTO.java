package com.example.demo.DTO;

import lombok.Data;

@Data
public class RegistDTO {
    private String account;
    private String password;
    private String name;
    private Integer age;
    private String token;
    private String newPassword;
}
