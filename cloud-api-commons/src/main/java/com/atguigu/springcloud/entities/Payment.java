package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * author: ZiChangHong
 * create-date: 2022/10/9 17:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;

    //private static final long serialVersionUID = 100868001L;
}
