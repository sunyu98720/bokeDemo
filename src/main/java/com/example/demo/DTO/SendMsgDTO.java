package com.example.demo.DTO;

import lombok.Data;

import javax.swing.border.TitledBorder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SendMsgDTO {
    @NotBlank(message = "标题不能为空")
    @NotNull(message = "标题不能为空")
    private String title;
    @NotBlank(message = "内容不能为空")
    @NotNull(message = "内容不能为空")
    private String content;
    @NotBlank(message = "图片不能为空")
    @NotNull(message = "图片不能为空")
    private String photoUrl;
}
