package com.chiz.yeosutalk.controller;

import com.chiz.yeosutalk.dto.MemberDto;
import com.chiz.yeosutalk.dto.MemberFormDto;
import com.chiz.yeosutalk.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /*
    * 회원가입 요청 컨트롤러
    * 회원가입에 성공하면 성공 알림 후 메인페이지로 이동
    */
    @PostMapping("/create")
    public String createMember(MemberFormDto memberFormDto) {
        Long memberId = memberService.createMember(memberFormDto);
        return "redirect:/";
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

    /*
    * 로그인 컨트롤러
    * 로그인 성공시 로그인 상태 세션에 담아서 메인페이지로 리다이렉트
    */
    @PostMapping("/login")
    @ResponseBody
    public String login(MemberFormDto memberFormDto, HttpServletRequest request) {
        MemberDto loginMember = memberService.login(memberFormDto.getAccountId(), memberFormDto.getPwd());

        if (loginMember == null) {
            return "fail";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginStatus", true);
        session.setAttribute("loginInfo", loginMember);
        return "success";
    }

    /*
    * 로그아웃 컨트롤러
    * 로그아웃 성공시 loginStatus = false
    * 메인페이지 리다이렉트
    */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginStatus");

        return "redirect:/";
    }
}
