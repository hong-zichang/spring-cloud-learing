package com.atguigu.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author: ZiChangHong
 * create-date: 2022/11/10 19:02
 **/
@Configuration
public class MySelfRule {

    @Bean
    public IRule getIRle(){
        return new RandomRule();
    }
}
