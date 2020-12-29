package com.example.cache.entity;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CommonResult {
    private Integer code;
    private Object data;
    private String msg;
}
