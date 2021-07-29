package org.example.tx.jpa.point.service;


import org.example.tx.jpa.point.domain.Point;

public interface IPointService {

    void incrementPoint(Point point, long addPoint);

}
