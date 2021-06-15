package com.dkf.springcloud.dao;

import com.dkf.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment queryinfo(Long id);
}
