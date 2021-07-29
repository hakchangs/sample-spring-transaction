package org.example.tx.jpa.payment.service;



import org.example.tx.jpa.payment.domain.Payment;

import java.util.List;

public interface IPaymentService {

    void pay(Payment payment) throws Exception;

    List<Payment> getAllByPayer(Long payerId);

}
