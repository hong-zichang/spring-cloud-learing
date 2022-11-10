package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author: ZiChangHong
 * create-date: 2022/11/11 14:19
 **/

@Configuration
public class FeginConfig {

    @Bean
    public Logger.Level getLoggerLevel(){
        return Logger.Level.FULL;
    }
}
