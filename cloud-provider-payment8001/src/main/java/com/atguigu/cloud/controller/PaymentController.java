package com.atguigu.cloud.controller;

import com.atguigu.cloud.service.PaymentService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * author: ZiChangHong
 * create-date: 2022/10/9 17:39
 **/
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果：{}", result);
        if (result > 0) {
            return new CommonResult(200, "数据插入成功,server.port:" + serverPort, result);
        }else {
            return new CommonResult(444, "数据插入失败", null);
        }
    }

    @GetMapping("getById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果：{}, 3366", result);
        if (result != null) {
            return new CommonResult(200, "数据查询成功, server.port:" + serverPort, result);
        }else {
            return new CommonResult(444, "数据查询失败", null);
        }
    }

    @GetMapping("discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("******element:" + service);
        }

        List<ServiceInstance> cloud_payment_service = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance serviceInstance : cloud_payment_service) {
            log.info(serviceInstance.getServiceId() + "\t" + serviceInstance.getHost() + "\t"
                    + serviceInstance.getPort() + "\t" + serviceInstance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("getTimeOut")
    public String getTimeOut(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return serverPort;
    }

}
