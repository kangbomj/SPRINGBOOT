//7주차 14페이지
//doain/Article.java 전체코드 재활용
//Table, class 이름 수정 article -> board
package com.example.demo.model.domain;

import lombok.*; //어노테이션 자동생성
import jakarta.persistence.*; // 기존 javax 후속 버전

@Getter // setter는 없음(무분별한 변경 x)
@Entity // 아래 객체와 DB 테이블을 매핑. JPA가 관리
@Table(name = "board") // 테이블 이름을 지정. 없는 경우 클래스이름으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부 생성자 접근 방지

public class Board {
    @Id // 기본 키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 1씩 증가

    //필드추가
    @Column(name = "content", nullable = false)
    private String content = "";
    @Column(name = "title", nullable = false)
    private String title = "";
    @Column(name = "count", nullable = false)
    private String count = "";
    @Column(name = "user", nullable = false)
    private String user = "";
    @Column(name = "likec", nullable = false)
    private String likec = "";
    @Column(name = "newdate", nullable = false)
    private String newdate = "";


    @Builder // 생성자에 빌더 패턴 적용(불변성)
        public Board(String title, String content){
        this.title = title;
        this.content = content;
        }

        //블로그 게시판 수정
        public void update(String title, String content){ //20241016 Update -> update로 수정
            //현재 객체 상태 업데이트
            this.title = title;
            this.content = content;
        }
}
