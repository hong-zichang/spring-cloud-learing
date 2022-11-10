package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * author: ZiChangHong
 * create-date: 2022/11/7 23:02
 *
 * ribbon = 负载均衡 + restTemplate
 *
 * restTemplate get/postForObject get/postForEntity 区别:后者更加详细，有状态码、请求头等信息
 **/

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        CommonResult commonResult = restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return commonResult;
    }

    @GetMapping("payment/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        CommonResult forObject = restTemplate.getForObject(PAYMENT_URL + "/payment/getById/" + id, CommonResult.class);
        return forObject;
    }

    @GetMapping("payment/getPaymentByEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/getById/" + id, CommonResult.class);
        if (!forEntity.getStatusCode().is2xxSuccessful()) {
            return new CommonResult<>(444, "请求失败！" + forEntity.getStatusCode() + " " + forEntity.getHeaders());
        }
        return forEntity.getBody();
    }

    @PostMapping("payment/createByEntity")
    public CommonResult<Payment> create2(@RequestBody Payment payment){
        ResponseEntity<CommonResult> Result = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if (!Result.getStatusCode().is2xxSuccessful()){
            return new CommonResult<>(444, "请求失败！" + Result.getStatusCode() + " " + Result.getHeaders());
        }
        return Result.getBody();
    }
}
