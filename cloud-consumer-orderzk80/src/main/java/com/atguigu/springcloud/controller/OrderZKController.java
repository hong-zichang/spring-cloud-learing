package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * author: ZiChangHong
 * create-date: 2022/11/9 23:44
 **/

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderZKController {
    private final String INVOKE_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("payment/zk")
    public String getZK(){
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        return result;
    }
}
