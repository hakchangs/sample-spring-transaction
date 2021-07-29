package org.example.tx.jpa.point.repository;

import org.example.tx.jpa.point.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
