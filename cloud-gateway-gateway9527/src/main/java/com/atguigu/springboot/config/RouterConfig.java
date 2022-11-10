package com.atguigu.springboot.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author: ZiChangHong
 * create-date: 2022/11/13 18:12
 **/
@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();
        builder.route("path_route_news_guonei",
                r -> r.path("/guonei").uri("https://news.baidu.com/guonei")).build();
        return builder.build();
    }

    @Bean
    public RouteLocator routeLocator2(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_news_guoji",
                r -> r.path("/guoji").uri("https://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
