package org.example.liamsblog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.liamsblog.dto.ApiResponse;
import org.example.liamsblog.dto.AuthResponse;
import org.example.liamsblog.dto.LoginRequest;
import org.example.liamsblog.dto.RegisterRequest;
import org.example.liamsblog.service.AuthService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request) {
        log.info("Received register request for username: {}", request.getUsername());
        try {
            AuthResponse response = authService.register(request);
            log.info("Registration successful for username: {}", request.getUsername());
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (RuntimeException e) {
            log.error("Registration failed for username: {}, error: {}", request.getUsername(), e.getMessage());
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(400, e.getMessage()));
        }
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        log.info("Received login request for username: {}", request.getUsername());
        try {
            AuthResponse response = authService.login(request);
            log.info("Login successful for username: {}", request.getUsername());
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (RuntimeException e) {
            log.error("Login failed for username: {}, error: {}", request.getUsername(), e.getMessage());
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(400, e.getMessage()));
        }
    }
} 