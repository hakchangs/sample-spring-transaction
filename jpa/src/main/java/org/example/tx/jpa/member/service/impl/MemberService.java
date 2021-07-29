package org.example.tx.jpa.member.service.impl;

import org.example.tx.jpa.member.domain.Member;
import org.example.tx.jpa.member.repository.MemberRepository;
import org.example.tx.jpa.member.service.IMemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements IMemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member getMember(Long id) {
        return memberRepository.findById(id).get();
    }
}
