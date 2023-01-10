package com.chiz.yeosutalk.controller;

import com.chiz.yeosutalk.dto.MemberFormDto;
import com.chiz.yeosutalk.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberController(MemberService memberService,
                            PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    /* 회원가입 뷰 컨트롤러 */
    @GetMapping("/create")
    public String createMemberForm() {
        return "members/createMemberForm";
    }

    /*
    * 회원가입 요청 컨트롤러
    * 회원가입에 성공하면 성공 알림 후 메인페이지로 이동
    */
    @PostMapping("/create")
    public String createMember(MemberFormDto memberFormDto) {
        log.info("가입 입력 정보 = (accountId={}, pwd={}, name={}, citizen={})", memberFormDto.getAccountId(), memberFormDto.getPwd(), memberFormDto.getName(), memberFormDto.getCitizen().toString());
        Long memberId = memberService.createMember(memberFormDto, passwordEncoder);

        return "redirect:/";
    }

    /* 아이디 중복 확인 컨트롤러 */
    @PostMapping("/id-check")
    @ResponseBody
    public boolean duplicateAccountId(@RequestParam String accountId) {
        /* true=중복, false=사용 가능 */
        return memberService.duplicateMember(accountId);
    }

    /* 로그인 뷰 컨트롤러 */
    @GetMapping("/login")
    public String loginMemberForm() {
        return "members/loginMemberForm";
    }

}
