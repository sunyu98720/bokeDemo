package com.example.demo.DTO;

import lombok.Data;

import javax.swing.border.TitledBorder;

@Data
public class SendMsgDTO {
    private String title;
    private String content;
    private String photoUrl;
}
