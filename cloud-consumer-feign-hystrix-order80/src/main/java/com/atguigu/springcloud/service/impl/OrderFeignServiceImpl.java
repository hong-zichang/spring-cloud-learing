package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.OrderFeignService;
import org.springframework.stereotype.Component;

/**
 * author: ZiChangHong
 * create-date: 2022/11/11 23:18
 **/
@Component
public class OrderFeignServiceImpl implements OrderFeignService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "服务调用失败, Feign fallback:paymentInfo_OK,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "服务调用失败, Feign fallback:paymentInfo_TimeOut,o(╥﹏╥)o";
    }
}
