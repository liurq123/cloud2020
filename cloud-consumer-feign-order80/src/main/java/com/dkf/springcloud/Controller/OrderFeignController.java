package com.dkf.springcloud.Controller;

import com.dkf.springcloud.entities.CommonResult;
import com.dkf.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/customer/payment/{id}")
    public CommonResult getResult(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @RequestMapping(value = "/customer/payment/timeout",method = RequestMethod.GET)
    public String paymentFeignTimeout(){
        return paymentFeignService.delayBack();
    }
}
