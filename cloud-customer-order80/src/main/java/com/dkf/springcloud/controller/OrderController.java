package com.dkf.springcloud.controller;

import com.dkf.springcloud.entities.CommonResult;
import com.dkf.springcloud.entities.Payment;
import com.dkf.springcloud.lb.LoadBalanced;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    // 远程调用地址   8001为对应服务的端口号
//    private static final String PAYMENY_URL = "http://localhost:8001";
    private static final String PAYMENY_URL = "http://CLOUD-PROVIDER-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalanced loadBalanced;

    @PostMapping("/customer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENY_URL+"/payment/create",payment,CommonResult.class);

    }

    @GetMapping("/customer/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENY_URL+"/payment/"+id,CommonResult.class);
    }

    @GetMapping("/customer/payment1/{id}")
    public CommonResult<Payment> getPaymentById1(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> resultResponseEntity = restTemplate.getForEntity(PAYMENY_URL+"/payment/"+id,CommonResult.class);
        if (resultResponseEntity.getStatusCode().is2xxSuccessful()){
            System.out.println(resultResponseEntity.toString());
            return resultResponseEntity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }


    @PostMapping("/customer/payment1/create")
    public CommonResult<Payment> create1(Payment payment){
        ResponseEntity<CommonResult> resultResponseEntity = restTemplate.postForEntity(PAYMENY_URL+"/payment/create",payment,CommonResult.class);
        if (resultResponseEntity.getStatusCode().is2xxSuccessful()){
            return resultResponseEntity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }

    }

    @GetMapping("/customer/payment/lb")
    public String getLbport(){
        // 获取eraka中注册的服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");

        if (instances != null && instances.size() > 0){
            ServiceInstance serviceInstance = loadBalanced.getService(instances);
            return restTemplate.getForObject(serviceInstance.getUri()+"/payment/serviceport",String.class);
        }else {
            return  null;
        }
    }


    // ====================> zipkin+sleuth
     @GetMapping("/consumer/payment/zipkin")
     public String paymentZipkin()
     {
         String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
         return result;
     }
}

