package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.dao.PaymentDao;
import com.atguigu.cloud.service.PaymentService;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author: ZiChangHong
 * create-date: 2022/10/9 17:37
 **/
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
