package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: ZiChangHong
 * create-date: 2022/10/9 17:19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer state;
    private String message;
    private T data;

    public CommonResult(Integer state, String message){
        this.state = state;
        this.message = message;
    }
}
