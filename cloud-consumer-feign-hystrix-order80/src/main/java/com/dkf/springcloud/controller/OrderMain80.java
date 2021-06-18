package com.dkf.springcloud.controller;

import com.dkf.springcloud.server.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentTimeOutFallback_Global_Method")
public class OrderMain80 {
    @Resource
    private OrderHystrixService orderHystrixService;

    @GetMapping("/customer/payment/ok/{id}")
    public String Paymentok(@PathVariable("id") Long id){
        return orderHystrixService.Paymentok(id);
    }


//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties =
//            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")})
    @GetMapping("/customer/payment/timeout/{id}")
    @HystrixCommand
    public String PaymentTimeout(@PathVariable("id") Long id){
//        int a = 10/0;
        return orderHystrixService.PaymentTimeout(id);
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Long id){
        return "我是消费者80，对付支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬) "+id;
    }
    public String paymentTimeOutFallback_Global_Method(){
        return "全局fallback处理方法，对方已经down机，我是消费者80，对付支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬) ";
    }
}
