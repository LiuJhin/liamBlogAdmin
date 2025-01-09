package org.example.liamsblog.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProjectResponse {
    private Long id;
    private String name;
    private String[] tags;
    private String imageUrl;
    private String responsibility;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String duration; // 开发周期（例如：3个月）
} 