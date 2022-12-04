package com.chiz.yeosutalk.repository;

import com.chiz.yeosutalk.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /* 닉네임으로 회원 조회 */
    Member findByNickname(String nickname);

    Member findByAccountId(String accountId);
}
