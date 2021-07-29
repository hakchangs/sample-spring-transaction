package org.example.tx.mybatis.payment.controller;

import org.example.tx.mybatis.payment.domain.Payment;
import org.example.tx.mybatis.payment.service.impl.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public void create(@RequestBody Payment payment) throws Exception {

        // 정상요청
//        paymentService.pay(payment);

        //case. Timeout
//        paymentService.testTimeout(payment);

        //case. 동일 Class 내 전파
        paymentService.testPropagation(payment);
    }

    @GetMapping
    public List<Payment> getByMember(@RequestParam Long memberId) {
        return paymentService.getAllByPayer(memberId);
    }

}
