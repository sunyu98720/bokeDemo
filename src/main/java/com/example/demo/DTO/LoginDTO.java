package com.example.demo.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String account;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    private String password;
}
