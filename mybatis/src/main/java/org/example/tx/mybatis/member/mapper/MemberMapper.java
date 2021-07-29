package org.example.tx.mybatis.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.tx.mybatis.member.domain.Member;

@Mapper
public interface MemberMapper {

    Member findById(Long id);

}
