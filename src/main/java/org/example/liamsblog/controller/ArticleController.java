package org.example.liamsblog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.liamsblog.dto.ArticleRequest;
import org.example.liamsblog.dto.ArticleResponse;
import org.example.liamsblog.service.ArticleService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponse> createArticle(@Valid @RequestBody ArticleRequest request) {
        return ResponseEntity.ok(articleService.createArticle(request));
    }

    @GetMapping
    public ResponseEntity<Page<ArticleResponse>> getArticles(
            @RequestParam(required = false) String title,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(articleService.getArticles(title, pageable));
    }
} 