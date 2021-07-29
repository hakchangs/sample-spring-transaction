package org.example.tx.mybatis.member.service.impl;

import org.example.tx.mybatis.member.domain.Member;
import org.example.tx.mybatis.member.mapper.MemberMapper;
import org.example.tx.mybatis.member.service.IMemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements IMemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Member getMember(Long id) {
        return memberMapper.findById(id);
    }

}
