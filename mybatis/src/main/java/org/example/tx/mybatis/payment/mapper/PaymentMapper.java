package org.example.tx.mybatis.payment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.tx.mybatis.payment.domain.Payment;

import java.util.List;

@Mapper
public interface PaymentMapper {

    void insert(Payment payment);

    List<Payment> findByPayer(Long payerId);

}
