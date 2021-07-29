package org.example.tx.jpa.member.controller;

import org.example.tx.jpa.member.domain.Member;
import org.example.tx.jpa.member.service.IMemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final IMemberService memberService;

    public MemberController(IMemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public Member get(@PathVariable Long id) {
        return memberService.getMember(id);
    }

}
