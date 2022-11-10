package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OrderFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * author: ZiChangHong
 * create-date: 2022/11/11 22:22
 **/
@RestController
@Slf4j
@RequestMapping("consumer/payment")
@DefaultProperties(defaultFallback = "GlobalHandlerMethod")
public class OrderHystrixController {

    @Resource
    private OrderFeignService orderFeignService;

    @GetMapping("hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return orderFeignService.paymentInfo_OK(id);
    }

    @GetMapping("hystrix/timeout/{id}")
    @HystrixCommand
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        int age = 10 / 0;
        return orderFeignService.paymentInfo_TimeOut(id);
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " 系统繁忙或者服务器错误,请稍后再试,id: " + id + "\to(╥﹏╥)o";
    }

    //Global Fallback Method
    public String GlobalHandlerMethod() {
        return "全局默认方法，线程池：" + Thread.currentThread().getName() + " 系统繁忙或者服务器错误,请稍后再试,id: " + "\to(╥﹏╥)o";
    }
}
