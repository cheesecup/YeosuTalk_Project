package com.chiz.yeosutalk.controller;

import com.chiz.yeosutalk.dto.MemberDto;
import com.chiz.yeosutalk.dto.MemberFormDto;
import com.chiz.yeosutalk.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /* 회원가입 요청 컨트롤러 */
    @PostMapping("/create")
    public Long createMember(MemberFormDto memberFormDto) {
        Long memberId = memberService.createMember(memberFormDto);
        return memberId;
    }

    /* 아이디 중복 확인 컨트롤러 */
    @PostMapping("/id-check")
    public boolean duplicateAccountId(@RequestParam("accountId") String accountId) {
        /* true=중복, false=사용 가능 */
        boolean result = memberService.duplicateMember(accountId);

        return result;
    }

    /* 로그인 컨트롤러 */
    @PostMapping("/login")
    public MemberDto login(@RequestParam("accountId") String accountId,
                           @RequestParam("pwd") String pwd) {
        return memberService.login(accountId, pwd);
    }

    /* 아이디 찾기 컨트롤러 */

    /* 비밀번호 찾기 컨트롤러 */


}
