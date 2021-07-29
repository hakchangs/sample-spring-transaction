package org.example.tx.jpa.payment.service.impl;

import org.example.tx.jpa.member.domain.Member;
import org.example.tx.jpa.member.repository.MemberRepository;
import org.example.tx.jpa.payment.domain.Payment;
import org.example.tx.jpa.payment.repository.PaymentRepository;
import org.example.tx.jpa.payment.service.IPaymentService;
import org.example.tx.jpa.point.domain.Point;
import org.example.tx.jpa.point.repository.PointRepository;
import org.example.tx.jpa.point.service.impl.PointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;

    private final PointService pointService;

    public PaymentService(PaymentRepository paymentRepository, MemberRepository memberRepository, PointRepository pointRepository, PointService pointService) {
        this.paymentRepository = paymentRepository;
        this.memberRepository = memberRepository;
        this.pointRepository = pointRepository;
        this.pointService = pointService;
    }

//    @Transactional(
////            isolation = Isolation.DEFAULT//TX 격리 레벨
////            propagation = Propagation.REQUIRED//TX 전파 범위
////            timeout = 3//초단위 시간제한
////            readOnly = false//읽기만 가능 - DB 벤더별로 호환이 다름
////            rollbackFor = {}//특정 예외에서만 롤백
////            noRollbackFor = {}//특정 예외 제외하고 롤백
////            rollbackForClassName = {}//특정 예외에서만 롤백
////            noRollbackForClassName = {}//특정 예외 제외하고 롤백
////            label = {}//???
////            transactionManager = ""//TX 매니저 Bean 이름 지정
//            /////////////////
//            // 아래서 테스트 //
//            /////////////////
////            noRollbackFor = RuntimeException.class
////            noRollbackForClassName = "RuntimeException"
////            rollbackFor = Exception.class
//    )
    @Override
    @Transactional(
            isolation = Isolation.DEFAULT,              //데이터 격리 레벨 - driver 마다 다름. 일반적으로 READ_COMMITTED 가 기본임. 
            propagation = Propagation.REQUIRED,         //TX 전파 규칙 - 메서드간 TX 전파규칙을 정의
            timeout = -1,                               //시간제한 - 시간이 지나면 TX 실패로 간주 (초단위)
            readOnly = false,                           //읽기전용 - 읽기만 수행할지 여부. 읽기전용=true 이면 변경작업시 오류. but, DB 벤더별로 호환여부가 다름.
            rollbackFor = {},                           //특정예외에서 롤백 - 롤백을 수행할 Exception 을 지정
            rollbackForClassName = {},                  //특정예외에서 롤백 - String 으로 설정
            noRollbackFor = {},                         //특정예외 제외하고 롤백 - 롤백 제외할 Exception 지정
            noRollbackForClassName = {},                //특정예외 제외하고 롤백 - String 으로 설정
            transactionManager = "transactionManager"   //TX 관리 Bean 의 이름 - TX 를 처리해줄 TX 관리 Bean 을 지정한다.
    )
    public void pay(Payment payment) throws Exception {

        //...비지니스 로직...

//        Member member = memberRepository.findById(payment.getPayer().getId()).get();
//        Point point = member.getPoint();
//
//        //1. 지불
//        payment.setPaidDate(new Timestamp(new Date().getTime()));
//        paymentRepository.save(payment);
//
//        //인위적으로 예외발생
//        if (1 == 1) {
//            throw new RuntimeException();
//        }
//        //일반 Exception
////        if (member.getId() == 1L) {
////            throw new Exception();
////        }
//
//        //2. 포인트 추가
//        long currentPoint = point.getAmount();
//        long addPoint = (long) Math.floor(payment.getAmount() * 0.01);
//        point.setAmount(currentPoint + addPoint);
////        pointRepository.save(point);

    }
    
    //
    //3초 넘으면 Rollback 하도록 설정
    // --> 10초후 Rollback
    //
    @Transactional(timeout = 3)
    public void testTimeout(Payment payment) throws InterruptedException {
        Member member = memberRepository.findById(payment.getPayer().getId()).get();
        Point point = member.getPoint();

        //1. 지불
        payment.setPaidDate(new Timestamp(new Date().getTime()));
        paymentRepository.save(payment);

        //
        //10초 딜레이
        //
        Thread.sleep(10 * 1000);

        //2. 포인트 추가
        long currentPoint = point.getAmount();
        long addPoint = (long) Math.floor(payment.getAmount() * 0.01);
        point.setAmount(currentPoint + addPoint);
//        pointRepository.save(point);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void testPropagation(Payment payment) {
        Member member = memberRepository.findById(payment.getPayer().getId()).get();
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
        paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllByPayer(Long payerId) {
        return paymentRepository.findAllByPayerId(payerId);
    }
}
