package com.dkf.springcloud.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderConsulController {

    @Resource
    private RestTemplate restTemplate;   // 已经通过配置类将对象注入bean池中  -- 跨服务调用方法

    public static final String URL = "http://consul-provider-payment";

    @GetMapping("/consumer/payment/consul")
    public String  payment(){
        String a = restTemplate.getForObject(URL+"/payment/consul",String.class);
        return  a;
    }



}
