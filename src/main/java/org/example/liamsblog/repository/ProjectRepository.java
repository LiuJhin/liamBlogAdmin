package org.example.liamsblog.repository;

import org.example.liamsblog.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByNameContainingOrTagsContaining(String name, String tags, Pageable pageable);
} 