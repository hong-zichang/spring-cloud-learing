package com.atguigu.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: ZiChangHong
 * create-date: 2022/11/10 19:39
 **/
@Component
@Slf4j
public class MyLoadBalance implements LoadBalance{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    protected int getNext(){
        int current, next;
        do {
            current = this.atomicInteger.get();
            next = current + 1 >= Integer.MAX_VALUE ? 0 : current + 1;
        }while (!atomicInteger.compareAndSet(current, next));
        log.info("********第{}次接受请求********", next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> list) {
        int next = getNext();
        next = next % list.size();
        ServiceInstance serviceInstance = list.get(next);
        log.info("请求处理的服务是：" + serviceInstance.getPort());
        log.info("****************************");
        return serviceInstance;
    }
}
