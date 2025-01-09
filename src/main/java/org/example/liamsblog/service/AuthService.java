package org.example.liamsblog.service;

import lombok.RequiredArgsConstructor;
import org.example.liamsblog.dto.AuthResponse;
import org.example.liamsblog.dto.LoginRequest;
import org.example.liamsblog.dto.RegisterRequest;
import org.example.liamsblog.entity.Admin;
import org.example.liamsblog.repository.AdminRepository;
import org.example.liamsblog.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // 检查用户名是否已存在
        if (adminRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }

        // 创建新用户
        Admin admin = new Admin();
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRole("ROLE_ADMIN");
        admin.setAvatar("default_avatar.png");
        admin.setGender("未知");
        admin.setIdentity("管理员");
        
        try {
            admin = adminRepository.save(admin);
        } catch (Exception e) {
            throw new RuntimeException("注册失败：" + e.getMessage());
        }

        // 生成token
        UserDetails userDetails = new User(admin.getUsername(), admin.getPassword(), new ArrayList<>());
        String token = jwtUtil.generateToken(userDetails);

        // 返回完整的响应
        return AuthResponse.builder()
                .token(token)
                .uid(admin.getId())
                .username(admin.getUsername())
                .avatar(admin.getAvatar())
                .gender(admin.getGender())
                .identity(admin.getIdentity())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        UserDetails userDetails = new User(admin.getUsername(), admin.getPassword(), new ArrayList<>());
        String token = jwtUtil.generateToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .uid(admin.getId())
                .username(admin.getUsername())
                .avatar(admin.getAvatar())
                .gender(admin.getGender())
                .identity(admin.getIdentity())
                .build();
    }
} 