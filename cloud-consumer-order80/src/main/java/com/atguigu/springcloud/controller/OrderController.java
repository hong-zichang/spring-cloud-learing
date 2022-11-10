package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

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

    @Resource
    private LoadBalance loadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("payment/getlb")
    public String getLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() == 0) {
            return null;
        }
        ServiceInstance instance = loadBalance.instance(instances);
        URI uri = instance.getUri();
        String forObject = restTemplate.getForObject(uri + "/payment/lb", String.class);
        return forObject;
    }
}
