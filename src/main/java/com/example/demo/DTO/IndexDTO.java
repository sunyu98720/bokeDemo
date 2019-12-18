package com.example.demo.DTO;

import com.example.demo.model.Publish;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class IndexDTO {
    /**
     * 页数大小
     */
    @NotNull(message = "size不能为空")
    @NotBlank(message = "size不能为空")
    private Integer size;

    /**
     * 索引
     */
    @NotNull(message = "index不能为空")
    @NotBlank(message = "index不能为空")
    private Integer index;

    /**
     * 发送内容
     */
    @NotNull(message = "内容不能为空")
    @NotBlank(message = "内容不能为空")
    private String content;


    /**
     * 页码
     */
    @NotNull(message = "页码不能为空")
    @NotBlank(message = "页码不能为空")
    private Integer page;

    /**
     * 页数和
     */
    private Integer pages;

    /**
     * 问题集合
     */
    private List<PublishDTO> publishDTOS;
}
