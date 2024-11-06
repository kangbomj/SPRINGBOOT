package com.example.demo.model.service;

    import lombok.RequiredArgsConstructor;
    import java.util.Optional;
    import java.util.List;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import com.example.demo.model.domain.Article;
    import com.example.demo.model.repository.BlogRepository;
    import com.example.demo.model.domain.Board;
    import com.example.demo.model.repository.BoardRepository;

    @Service
    @RequiredArgsConstructor // 생성자 자동 생성(부분)
    public class BlogService {
        @Autowired 
        //private final BlogRepository blogRepository;
        private final BoardRepository blogRepository;
        //20241106 7주차 Board 수정

        public List<Board> findAll() { // 게시판 전체 목록 조회
        return blogRepository.findAll();
        }

        public Optional<Board> findById(Long id) {
            return blogRepository.findById(id);
        }


        // public Article save(AddArticleRequest request){
        //     // DTO가 없는 경우 이곳에 직접 구현 가능
        //     //일반적으로 서비스는 DTO 구현하지 않는다.
        //     // public ResponseEntity<Article> addArticle(@RequestParam String title, @RequestParam String content) {
        //     // Article article = Article.builder()
        //     // .title(title)
        //     // .content(content)
        //     // .build();
        //     return blogRepository.save(request.toEntity());
        //     }
            
        //     //게시판 수정 20241016 6주차
        //     public Optional<Article> findById(Long id){
        //         return blogRepository.findById(id);//게시판 특정 글 조회
        //     }

        //     public void update(Long id, AddArticleRequest request){
        //         Optional<Article> optionalArticle = blogRepository.findById(id); //단일글조회
        //         optionalArticle.ifPresent(article -> { //값이 있으면
        //             article.update(request.getTitle(), request.getContent()); //값을 수정
        //             blogRepository.save(article); //Article 객체에 저장
        //         });
        //     }

        //     public void delete(Long id){
        //         blogRepository.deleteById(id);
        //     }
    }


    //6주차까지 코드
    // @Service
    // @RequiredArgsConstructor // 생성자 자동 생성(부분)
    // public class BlogService {
    //     @Autowired 
    //     //private final BlogRepository blogRepository;
    //     private final BoardRepository blogRepository;
    //     //20241106 7주차 Board 수정

    //     public List<Article> findAll() { // 게시판 전체 목록 조회
    //     return blogRepository.findAll();
    //     }


    //     public Article save(AddArticleRequest request){
    //         // DTO가 없는 경우 이곳에 직접 구현 가능
    //         //일반적으로 서비스는 DTO 구현하지 않는다.
    //         // public ResponseEntity<Article> addArticle(@RequestParam String title, @RequestParam String content) {
    //         // Article article = Article.builder()
    //         // .title(title)
    //         // .content(content)
    //         // .build();
    //         return blogRepository.save(request.toEntity());
    //         }
            
    //         //게시판 수정 20241016 6주차
    //         public Optional<Article> findById(Long id){
    //             return blogRepository.findById(id);//게시판 특정 글 조회
    //         }

    //         public void update(Long id, AddArticleRequest request){
    //             Optional<Article> optionalArticle = blogRepository.findById(id); //단일글조회
    //             optionalArticle.ifPresent(article -> { //값이 있으면
    //                 article.update(request.getTitle(), request.getContent()); //값을 수정
    //                 blogRepository.save(article); //Article 객체에 저장
    //             });
    //         }

    //         public void delete(Long id){
    //             blogRepository.deleteById(id);
    //         }
    // }