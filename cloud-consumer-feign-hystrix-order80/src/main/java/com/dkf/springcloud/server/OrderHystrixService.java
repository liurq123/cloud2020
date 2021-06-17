package com.dkf.springcloud.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface OrderHystrixService {
    @GetMapping("/payment/ok/{id}")
    public String Paymentok(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout/{id}")
    public String PaymentTimeout(@PathVariable("id") Long id);
}
