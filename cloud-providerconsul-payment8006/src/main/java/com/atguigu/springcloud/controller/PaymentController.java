package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * author: ZiChangHong
 * create-date: 2022/11/10 15:41
 **/

@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("consul")
    public String getConsul(){
        return "Spring Cloud With Consul:" + serverPort + "\t" + UUID.randomUUID();
    }
}
