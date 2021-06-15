package com.dkf.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// 注解类
@Configuration
public class ApplicationContextConfig {

    // 将对象注入到bean中
    @Bean
//    @

    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
