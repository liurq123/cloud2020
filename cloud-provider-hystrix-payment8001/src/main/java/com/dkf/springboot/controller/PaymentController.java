package com.dkf.springboot.controller;

import com.dkf.springboot.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/ok/{id}")
    public String Paymentok(@PathVariable("id") Long id){
        String result = paymentService.Paymentok(id);
        log.info("*******result:"+result);
        return result;
    }

    @GetMapping("/payment/timeout/{id}")
    public String PaymentTimeout(@PathVariable("id") Long id){
        String result = paymentService.PaymentTimeout(id);
        log.info("*******result:"+result);
        return result ;
    }

    //===服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.PaymentCircultBreaker(id);
        log.info("*******result:"+result);
        return result;
    }


}
