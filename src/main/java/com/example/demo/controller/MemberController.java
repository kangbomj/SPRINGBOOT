package com.example.demo.controller;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.model.domain.Member;
import com.example.demo.model.service.MemberService;
import com.example.demo.model.service.AddMemberRequest;
import com.example.demo.model.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
public class MemberController {

    // private final MemberService memberService;
    // private final MemberRepository memberRepository;
    // private final PasswordEncoder passwordEncoder;

    // public MemberController(MemberService memberService, MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
    //     this.memberService = memberService;
    //     this.memberRepository = memberRepository;
    //     this.passwordEncoder = passwordEncoder;
    // }

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join_new") //회원가입페이지
    public String join_new() {
        return "join_new";
    }

    //CHATGPT 코드
    //검증실패시 적절히 처리하도록 수정
    @PostMapping("/api/members") //저장
    public String addmembers(@Valid @ModelAttribute AddMemberRequest request, BindingResult bindingResult, Model model) {
        //검증 실패 시 오류 처리
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "join_new";
        }

        try {
            memberService.saveMember(request); // AddMemberRequest 타입으로 전달
            return "join_end"; // 성공 시 페이지 이동
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage()); // 중복 회원 예외 처리
            return "join_new"; // 실패 시 회원가입 페이지로 이동
        }
    }

    @GetMapping("/login") //로그인페이지
    public String login() {
        return "login";
    }
    
    @PostMapping("/api/login_check")
    public String checkMembers(@ModelAttribute AddMemberRequest request, Model model, HttpSession session) {
        try {    
            Member member = memberService.loginCheck(request.getEmail(), request.getPassword());
            String sessionId = UUID.randomUUID().toString();
            String email = request.getEmail();
            session.setAttribute("userID", sessionId);
            session.setAttribute("email", email);
            model.addAttribute("member", member); //정보 전달
            return "redirect:/board_list";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            //페이지 안넘어가서 일단 싹다 보드리스트로 넘어가게 해둠 241212
            // return "login";
            return "redirect:/board_list";
        }
    }

    @GetMapping("/api/logout") // 로그아웃 버튼 동작
    public String member_logout(Model model, HttpServletRequest request2, HttpServletResponse response) {
        try {
            HttpSession session = request2.getSession(false); // 기존 세션 가져오기(존재하지 않으면 null 반환)
            session.invalidate(); // 기존 세션 무효화
            //Cookie cookie = new Cookie("JSESSIONID", null); // JSESSIONID is the default session cookie name
            //cookie.setPath("/"); // Set the path for the cookie
            //cookie.setMaxAge(0); // Set cookie expiration to 0 (removes the cookie)
            //response.addCookie(cookie); // Add cookie to the response
            session = request2.getSession(true); // 새로운 세션 생성
            System.out.println("세션 userId: " + session.getAttribute("userId" )); // 초기화 후 IDE 터미널에 세션 값 출력
            return "login"; // 로그인 페이지로 리다이렉트
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage()); // 에러 메시지 전달
            return "login"; // 로그인 실패 시 로그인 페이지로 리다이렉트
        }
    }
}
