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
            session.setAttribute("userID", sessionId);
            model.addAttribute("member", member); //정보 전달
            return "redirect:/board_list";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}
