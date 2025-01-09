package org.example.liamsblog.service;

import lombok.RequiredArgsConstructor;
import org.example.liamsblog.dto.ProjectResponse;
import org.example.liamsblog.entity.Project;
import org.example.liamsblog.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Page<ProjectResponse> getProjects(String keyword, Pageable pageable) {
        Page<Project> projects;
        if (keyword != null && !keyword.isEmpty()) {
            projects = projectRepository.findByNameContainingOrTagsContaining(keyword, keyword, pageable);
        } else {
            projects = projectRepository.findAll(pageable);
        }
        return projects.map(this::convertToResponse);
    }

    private ProjectResponse convertToResponse(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setTags(project.getTags().split(","));
        response.setImageUrl(project.getImageUrl());
        response.setResponsibility(project.getResponsibility());
        response.setStartDate(project.getStartDate());
        response.setEndDate(project.getEndDate());
        
        // 计算开发周期
        if (project.getStartDate() != null && project.getEndDate() != null) {
            long days = Duration.between(project.getStartDate(), project.getEndDate()).toDays();
            long months = days / 30;
            if (months > 0) {
                response.setDuration(months + "个月");
            } else {
                response.setDuration(days + "天");
            }
        }
        
        return response;
    }
} 