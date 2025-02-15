package org.example.liamsblog.repository;

import org.example.liamsblog.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByTitleContaining(String title, Pageable pageable);
} 