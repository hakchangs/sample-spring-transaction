package org.example.tx.mybatis.point.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.tx.mybatis.point.domain.Point;

@Mapper
public interface PointMapper {

    void update(Point point);

}
