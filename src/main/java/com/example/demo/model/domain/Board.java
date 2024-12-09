//7주차 14페이지
//doain/Article.java 전체코드 재활용
//Table, class 이름 수정 article -> board
package com.example.demo.model.domain;

import lombok.*; //어노테이션 자동생성
import jakarta.persistence.*; // 기존 javax 후속 버전

@Getter
@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title = "";

    @Column(name = "content", nullable = false)
    private String content = "";

    @Column(name = "user", nullable = false)
    private String user = "";

    @Column(name = "newdate", nullable = false)
    private String newdate = "";

    @Column(name = "count", nullable = false)
    private String count = "";

    @Column(name = "likecount", nullable = false)
    private String likecount = "";

    public Board(String title, String content, String user, String newdate, String count, String likecount) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.newdate = newdate;
        this.count = count;
        this.likecount = likecount;
    }

    public void update(String title, String content, String user, String newdate, String count, String likecount) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.newdate = newdate;
        this.count = count;
        this.likecount = likecount;
    }

}