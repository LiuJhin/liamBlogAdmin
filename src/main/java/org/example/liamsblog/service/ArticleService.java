package org.example.liamsblog.service;

import lombok.RequiredArgsConstructor;
import org.example.liamsblog.dto.ArticleRequest;
import org.example.liamsblog.dto.ArticleResponse;
import org.example.liamsblog.entity.Article;
import org.example.liamsblog.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleResponse createArticle(ArticleRequest request) {
        Article article = new Article();
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        // 获取当前登录用户作为作者
        String author = SecurityContextHolder.getContext().getAuthentication().getName();
        article.setAuthor(author);
        
        Article savedArticle = articleRepository.save(article);
        return convertToResponse(savedArticle);
    }

    public Page<ArticleResponse> getArticles(String title, Pageable pageable) {
        Page<Article> articles;
        if (title != null && !title.isEmpty()) {
            articles = articleRepository.findByTitleContaining(title, pageable);
        } else {
            articles = articleRepository.findAll(pageable);
        }
        return articles.map(this::convertToResponse);
    }

    private ArticleResponse convertToResponse(Article article) {
        ArticleResponse response = new ArticleResponse();
        response.setId(article.getId());
        response.setTitle(article.getTitle());
        response.setContent(article.getContent());
        response.setAuthor(article.getAuthor());
        response.setCreateTime(article.getCreateTime());
        response.setUpdateTime(article.getUpdateTime());
        return response;
    }
} 