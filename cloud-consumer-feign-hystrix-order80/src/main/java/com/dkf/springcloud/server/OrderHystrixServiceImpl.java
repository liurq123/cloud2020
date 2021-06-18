package com.dkf.springcloud.server;

import org.springframework.stereotype.Component;

@Component
public class OrderHystrixServiceImpl implements OrderHystrixService {
    @Override
    public String Paymentok(Long id) {
        return "--------------Paymentok  fallback 降级调用";
    }

    @Override
    public String PaymentTimeout(Long id) {
        return "--------------PaymentTimeout  fallback 降级调用";
    }
}
