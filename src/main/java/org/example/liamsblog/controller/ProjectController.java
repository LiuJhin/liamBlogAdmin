package org.example.liamsblog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.liamsblog.dto.ApiResponse;
import org.example.liamsblog.dto.ProjectResponse;
import org.example.liamsblog.service.ProjectService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Page<ProjectResponse>>> getProjects(
            @RequestParam(required = false) String keyword,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        log.info("Received project list request with keyword: {}", keyword);
        try {
            Page<ProjectResponse> projects = projectService.getProjects(keyword, pageable);
            return ResponseEntity.ok(ApiResponse.success(projects));
        } catch (Exception e) {
            log.error("Failed to get projects, error: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(400, "获取项目列表失败：" + e.getMessage()));
        }
    }
} 