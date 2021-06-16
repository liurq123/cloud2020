package com.dkf.springcloud.service;

import com.dkf.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author LiuRongQuan
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-SERVICE")    // 提供服务的服务名称
public interface PaymentFeignService {
    @GetMapping(value = "/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

//    @RequestMapping(value = "/payment/timeout",method = RequestMethod.POST)
//    public String delayBack();

    @RequestMapping(value = "/payment/timeout",method = RequestMethod.GET)
    public String delayBack();

}
