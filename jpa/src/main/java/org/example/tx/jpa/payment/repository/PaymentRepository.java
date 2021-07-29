package org.example.tx.jpa.payment.repository;

import org.example.tx.jpa.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByPayerId(Long payerId);

}
