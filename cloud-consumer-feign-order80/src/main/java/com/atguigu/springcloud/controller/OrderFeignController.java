package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * author: ZiChangHong
 * create-date: 2022/11/11 13:47
 **/
@RestController
@Slf4j
@RequestMapping("consumer/payment")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment){
        CommonResult commonResult = paymentFeignService.create(payment);
        return commonResult;
    }

    @GetMapping("getById/{id}")
    CommonResult<Payment> getById(@PathVariable("id") Long id){
        CommonResult<Payment> paymentCommonResult = paymentFeignService.getPaymentById(id);
        return paymentCommonResult;
    }

    @GetMapping("getTimeOut")
    String getTimeOut(){
        return paymentFeignService.getTimeOut();
    }
}
