package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
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
import com.example.demo.model.domain.Article;
import com.example.demo.model.domain.Board;
import com.example.demo.model.service.BlogService;
import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.AddBoardRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

@Controller
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/article_list")
    public String article_list(Model model) {
        List<Article> list = blogService.findAll();
        model.addAttribute("articles", list);
        return "article_list";
    }

    @GetMapping("/board_list")
    public String board_list(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            HttpSession session) {

        //오류 해결못하겠어서 일단 로그인 하면 보드리스트로 넘어가는거 막아뒀음. index -> board_list
        // String userId = (String) session.getAttribute("userId");
        // String email = (String) session.getAttribute("email");

        // if (userId == null) {
        //     return "redirect:/login";
        // }
        
        // System.out.println("세션 userId : " + userId);
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Board> list;

        if (keyword.isEmpty()) {
            list = blogService.findAllBoard(pageable);
        } else {
            list = blogService.searchByKeyword(keyword, pageable);
        }

        int startNum = (page * pageSize) + 1;
        
        model.addAttribute("boards", list);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("startNum", startNum);
        //model.addAttribute("email", email);
        return "board_list";
    }

    @GetMapping("/article_edit/{id}")
    public String article_edit(Model model, @PathVariable Long id) {
        Optional<Article> list = blogService.findById(id);
        if (list.isPresent()) {
            model.addAttribute("article", list.get());
        } else {
            return "/article_error";
        }
        return "article_edit";
    }

    @GetMapping("/board_edit/{id}")
    public String board_edit(Model model, @PathVariable Long id) {
        Optional<Board> list = blogService.findByIdBoard(id);
        if (list.isPresent()) {
            model.addAttribute("board", list.get());
        } else {
            return "article_error";
        }
        return "board_edit";
    }

    @GetMapping("/board_view/{id}")
    public String board_view(Model model, @PathVariable Long id) {
        Optional<Board> list = blogService.findByIdBoard(id);
        if (list.isPresent()) {
            model.addAttribute("boards", list.get());
        } else {
            return "article_error";
        }
        return "board_view";
    }

    @GetMapping("/board_write")
    public String board_write() {
        return "board_write";
    }
    
    @PostMapping("/api/articles")
    public String addArticle(@ModelAttribute AddArticleRequest request) {
        blogService.save(request);
        return "redirect:/article_list";
    }

    @PostMapping("/api/boards")
    public String addBoard(@ModelAttribute AddBoardRequest request) {
        blogService.saveBoard(request);
        return "redirect:/board_list";
    }

    @PutMapping("/api/article_edit/{id}")
    public String updateArticle(
            @PathVariable Long id,
            @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/article_list";
    }

    @PutMapping("/api/board_edit/{id}")
    public String updateBoard(
            @PathVariable Long id,
            @ModelAttribute AddBoardRequest request) {
        blogService.updateBoard(id, request);
        return "redirect:/board_list";
    }

    @DeleteMapping("/api/article_delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/article_list";
    }

    @DeleteMapping("/api/board_delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        blogService.deleteBoard(id);
        return "redirect:/board_list";
    }

}