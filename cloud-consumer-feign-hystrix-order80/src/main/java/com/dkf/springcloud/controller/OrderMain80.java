package com.dkf.springcloud.controller;

import com.dkf.springcloud.server.OrderHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderMain80 {
    @Resource
    private OrderHystrixService orderHystrixService;

    @GetMapping("/customer/payment/ok/{id}")
    public String Paymentok(@PathVariable("id") Long id){
        return orderHystrixService.Paymentok(id);
    }

    @GetMapping("/customer/payment/timeout/{id}")
    public String PaymentTimeout(@PathVariable("id") Long id){
        return orderHystrixService.PaymentTimeout(id);
    }
}
