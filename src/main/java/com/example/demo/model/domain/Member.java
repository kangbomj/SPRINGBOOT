package com.example.demo.model.domain;

import jakarta.persistence.Id;
import lombok.*; //어노테이션 자동생성
import jakarta.persistence.*; // 기존 javax 후속 버전


public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "email", unique = true, nullable = false) //Unique 중복 X
    private String email = "";

    @Column(name = "password", nullable = false)
    private String password = "";

    @Column(name = "age", nullable = false)
    private String age = "";

    @Column(name = "mobile", nullable = false)
    private String mobile = "";

    @Column(name = "address", nullable = false)
    private String address = "";

    @Builder
    public Member(String name, String email, String password, String age, String mobile, String address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.mobile = mobile;
        this.address = address;
    }

}
