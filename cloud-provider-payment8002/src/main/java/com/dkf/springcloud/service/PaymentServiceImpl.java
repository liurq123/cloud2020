package com.dkf.springcloud.service;

import com.dkf.springcloud.entities.Payment;
import org.springframework.stereotype.Service;


public interface PaymentServiceImpl {
    public int create(Payment payment);

    public Payment queryinfo(Long id);
}
