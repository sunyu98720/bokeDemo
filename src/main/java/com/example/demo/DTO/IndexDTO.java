package com.example.demo.DTO;

import com.example.demo.model.Publish;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class IndexDTO {
    private Integer size;
    private Integer index;
    private String content;
    private Integer page;
    private Integer pages;
    private List<PublishDTO> publishDTOS;
}
