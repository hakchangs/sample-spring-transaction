package org.example.tx.mybatis.point.service;

import org.example.tx.mybatis.point.domain.Point;

public interface IPointService {

    void incrementPoint(Point point, long addPoint);

}
