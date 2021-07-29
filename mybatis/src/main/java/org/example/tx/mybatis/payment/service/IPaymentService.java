package org.example.tx.mybatis.payment.service;

import org.example.tx.mybatis.payment.domain.Payment;

import java.util.List;

public interface IPaymentService {

    void pay(Payment payment) throws Exception;

    List<Payment> getAllByPayer(Long payerId);

}
