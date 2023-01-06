package com.chiz.yeosutalk.controller;

import com.chiz.yeosutalk.dto.MemberDto;
import com.chiz.yeosutalk.dto.MemberFormDto;
import com.chiz.yeosutalk.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/member")
@CrossOrigin(originPatterns = "http://localhost:8090")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /* 회원가입 뷰 컨트롤러 */
    @GetMapping("/create")
    public String createMemberForm() {
        return "members/createMemberForm";
    }

    /* 회원가입 요청 컨트롤러 */
    @PostMapping("/create")
    @ResponseBody
    public Long createMember(MemberFormDto memberFormDto) {
        Long memberId = memberService.createMember(memberFormDto);
        return memberId;
    }

    /* 아이디 중복 확인 컨트롤러 */
    @PostMapping("/id-check")
    @ResponseBody
    public boolean duplicateAccountId(@RequestParam String accountId) {
        /* true=중복, false=사용 가능 */
        boolean result = memberService.duplicateMember(accountId);

        return result;
    }

    /* 로그인 뷰 컨트롤러 */
    @GetMapping("/login")
    public String loginMemberForm() {
        return "members/loginMemberForm";
    }

    /* 로그인 컨트롤러 */
    @PostMapping("/login")
    @ResponseBody
    public MemberDto login(@RequestBody MemberFormDto memberFormDto, HttpSession session) {
        MemberDto loginMember = memberService.login(memberFormDto.getAccountId(), memberFormDto.getPwd());
        loginMember.setId(null);
        loginMember.setPwd(null);

        session.setAttribute("loginMember", loginMember);
        return loginMember;
    }
}
