package com.dkf.springboot.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {


    public String Paymentok(Long id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id：  "+id+"\t"+"哈哈哈";
    }

    @HystrixCommand(fallbackMethod = "PaymentTimeoutHandle",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String PaymentTimeout(Long id){
        int timeNum = 5;    // 故意超时，触发降级
                // 超时  或者 此方法异常都会触发fallback指定的降级方法
//        try {
//            TimeUnit.SECONDS.sleep(timeNum);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+id+"\t"+"呜呜呜"+" 耗时(秒)"+timeNum;
    }

    public String PaymentTimeoutHandle(Long id){
        return "线程池："+Thread.currentThread().getName()+"   系统繁忙，请稍后重试,id：  "+id+"\t"+"嘤嘤嘤"+" 耗时(秒)";
    }

    @HystrixCommand(fallbackMethod = "PaymentCircultBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String PaymentCircultBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("*********id 不能为负数");
        }
        String UUID = IdUtil.randomUUID();    // hutool 工具生成的唯一流水号
        return "线程名："+Thread.currentThread().getName()+" 调用成功！流水号："+UUID;

    }

    public String PaymentCircultBreaker_fallback(Integer id){   // 降级别的fallback方法
        return "id 不能为负数,请稍后重试！::>_<::   id:"+id;
    }

}
