package org.example.tx.mybatis.payment.service.impl;

import org.example.tx.mybatis.member.domain.Member;
import org.example.tx.mybatis.member.mapper.MemberMapper;
import org.example.tx.mybatis.payment.domain.Payment;
import org.example.tx.mybatis.payment.mapper.PaymentMapper;
import org.example.tx.mybatis.payment.service.IPaymentService;
import org.example.tx.mybatis.point.domain.Point;
import org.example.tx.mybatis.point.mapper.PointMapper;
import org.example.tx.mybatis.point.service.impl.PointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    private final PaymentMapper paymentMapper;
    private final MemberMapper memberMapper;
    private final PointMapper pointMapper;

    private final PointService pointService;

    public PaymentService(PaymentMapper paymentMapper, MemberMapper memberMapper, PointMapper pointMapper, PointService pointService) {
        this.paymentMapper = paymentMapper;
        this.memberMapper = memberMapper;
        this.pointMapper = pointMapper;
        this.pointService = pointService;
    }

    @Transactional(
//            isolation = Isolation.DEFAULT//TX 격리 레벨
//            propagation = Propagation.REQUIRED//TX 전파 범위
//            timeout = 3//초단위 시간제한
//            readOnly = false//읽기만 가능 - DB 벤더별로 호환이 다름
//            rollbackFor = {}//특정 예외에서만 롤백
//            noRollbackFor = {}//특정 예외 제외하고 롤백
//            rollbackForClassName = {}//특정 예외에서만 롤백
//            noRollbackForClassName = {}//특정 예외 제외하고 롤백
//            label = {}//???
//            transactionManager = ""//TX 매니저 Bean 이름 지정
            /////////////////
            // 아래서 테스트 //
            /////////////////
//            noRollbackFor = RuntimeException.class
//            noRollbackForClassName = "RuntimeException"
//            rollbackFor = Exception.class
    )
    @Override
    public void pay(Payment payment) throws Exception {

        Member member = memberMapper.findById(payment.getPayer().getId());
        Point point = member.getPoint();

        //1. 지불
        payment.setPaidDate(new Timestamp(new Date().getTime()));
        paymentMapper.insert(payment);

        //인위적으로 예외발생
//        if (1 == 1) {
//            throw new RuntimeException();
//        }
        //일반 Exception
        if (member.getId() == 1L) {
            throw new Exception();
        }

        //2. 포인트 추가
        long currentPoint = point.getAmount();
        long addPoint = (long) Math.floor(payment.getAmount() * 0.01);
        point.setAmount(currentPoint + addPoint);
        pointMapper.update(point);

    }
    
    //
    //3초 넘으면 Rollback 하도록 설정
    // --> 실행시, 10초후 Rollback
    //
    @Transactional(timeout = 3)
    public void testTimeout(Payment payment) throws InterruptedException {
        Member member = memberMapper.findById(payment.getPayer().getId());
        Point point = member.getPoint();

        //1. 지불
        payment.setPaidDate(new Timestamp(new Date().getTime()));
        paymentMapper.insert(payment);

        //
        //10초 딜레이
        //
        Thread.sleep(10 * 1000);

        //2. 포인트 추가
        long currentPoint = point.getAmount();
        long addPoint = (long) Math.floor(payment.getAmount() * 0.01);
        point.setAmount(currentPoint + addPoint);
        pointMapper.update(point);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void testPropagation(Payment payment) {
        Member member = memberMapper.findById(payment.getPayer().getId());
        Point point = member.getPoint();

        //1. 포인트 추가
        long addPoint = (long) Math.floor(payment.getAmount() * 0.01);
        pointService.incrementPoint(point, addPoint);

        //인위적으로 예외발생
        if (1 == 1) {
            throw new RuntimeException();
        }

        //2. 지불
        doPayment(payment);

    }

    /**
     * 지불 부분만 분리(pay)
     */
//    @Transactional
    private void doPayment(Payment payment) {
        payment.setPaidDate(new Timestamp(new Date().getTime()));
        paymentMapper.insert(payment);
    }

    @Override
    public List<Payment> getAllByPayer(Long payerId) {
        return paymentMapper.findByPayer(payerId);
    }
}
