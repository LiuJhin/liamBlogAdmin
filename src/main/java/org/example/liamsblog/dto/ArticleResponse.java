package org.example.liamsblog.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 