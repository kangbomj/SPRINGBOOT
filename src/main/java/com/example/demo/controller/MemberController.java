package com.example.demo.controller;

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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberController(MemberService memberService, MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/join_new") //회원가입페이지
    public String join_new(){
        return "join_new";
    }

    // @PostMapping("/api/members") //저장
    // public String addmembers(@ModelAttribute MemberService request){
    //     memberServie.saveMember(request);
    //     return "join_end";
    //}

    @GetMapping("/member_login") //로그인페이지
    public String member_login(){
        return "login";
    }

    @PostMapping("/api/login_check")
    public String checkMembers(@ModelAttribute AddMemberRequest request, Model model){
        try{
            Member member = memberService.loginCheck(request.getEmail(), request.getPassword());
            model.addAttribute("member", member); //정보 전달
            return "redirect:/board_list";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    public Member loginCheck(String email, String rawPassword){
        Member member = memberRepository.findByEmail(email);
        if (member == null){
            throw new IllegalArgumentException("등록되지 않은 이메일");
        }

        if (!passwordEncoder.matches(rawPassword, member.getPassword())){
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        return member;
    }
}
