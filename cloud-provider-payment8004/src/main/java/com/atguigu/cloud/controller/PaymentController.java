package com.atguigu.cloud.controller;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: ZiChangHong
 * create-date: 2022/11/9 23:17
 **/
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("zk")
    public String getZookeeper(){
        return "springcloud with zookeeper: " + serverPort + "\t" + UUID.randomUUID();
    }
}
