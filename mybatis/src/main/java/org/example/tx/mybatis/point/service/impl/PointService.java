package org.example.tx.mybatis.point.service.impl;

import org.example.tx.mybatis.point.domain.Point;
import org.example.tx.mybatis.point.mapper.PointMapper;
import org.example.tx.mybatis.point.service.IPointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PointService implements IPointService {

    private final PointMapper pointMapper;

    public PointService(PointMapper pointMapper) {
        this.pointMapper = pointMapper;
    }

    //기본: @Transactional 명시하지 않아도, 부모의 TX 를 따름.
//    @Transactional(propagation = Propagation.REQUIRES_NEW)//별도의 TX 생성 - 부모 수행과 관계없이 Commit 됨
//    @Transactional(propagation = Propagation.MANDATORY)//TX 를 달고들어와야 수행가능. 없으면 Exception 발생
//    @Transactional(propagation = Propagation.NESTED)//TX 를 하위로 중첩시킴 - 부모의 영향 받음, 자신은 부모에 영향 안줌.
//    @Transactional(propagation = Propagation.NEVER)//TX 를 사용하지 않음. 전파도 허용안함 - 진행중인 TX 있으면, Exception 발생
//    @Transactional(propagation = Propagation.REQUIRED)//default 설정 - TX 전파받음
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)//TX 를 사용하지 않음.
//    @Transactional(propagation = Propagation.SUPPORTS)//TX 있으면 사용, 없으면 사용안함 - 전파한 TX 있는지 여부에 따름
    @Override
    public void incrementPoint(Point point, long addPoint) {

        long currentPoint = point.getAmount();

        point.setAmount(currentPoint + addPoint);
        pointMapper.update(point);

//        //인위적으로 예외발생
//        if (1 == 1) {
//            throw new RuntimeException();
//        }
    }
}
