package org.example.tx.jpa.member.repository;

import org.example.tx.jpa.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
