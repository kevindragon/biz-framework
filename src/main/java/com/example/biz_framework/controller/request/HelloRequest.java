package com.example.biz_framework.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HelloRequest {
    @NotBlank(message = "名字不能为空")
    private String name;

    @Min(value = 6, message = "至少需要6岁")
    private Integer age;
}
