package com.dkf.springcloud.service;

import com.dkf.springcloud.dao.PaymentDao;
import com.dkf.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentService implements PaymentServiceImpl  {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment queryinfo(Long id) {
        return paymentDao.queryinfo(id);
    }
}
