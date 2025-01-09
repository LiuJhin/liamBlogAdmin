package org.example.liamsblog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "admins")
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String role;

    @Column(length = 200)
    private String avatar;

    @Column(length = 10)
    private String gender;

    @Column(length = 50)
    private String identity;
} 