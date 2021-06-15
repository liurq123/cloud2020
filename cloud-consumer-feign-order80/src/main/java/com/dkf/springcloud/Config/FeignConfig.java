package com.dkf.springcloud.Config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feijLevel(){
        return Logger.Level.FULL;
    }
}
