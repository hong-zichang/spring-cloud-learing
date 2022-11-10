package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * author: ZiChangHong
 * create-date: 2022/11/10 15:51
 **/
@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {

    public static final String SERVER_URL = "http://consul-provider-payment";

    @Resource
    public RestTemplate restTemplate;

    @GetMapping("payment/consul")
    public String getPaymentConsul(){
        String forObject = restTemplate.getForObject(SERVER_URL + "/payment/consul", String.class);
        return forObject + " by consumer with Consul";
    }
}
