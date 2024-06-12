package com.example.newswebapp.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.newswebapp.Service.AuthService;
import com.example.newswebapp.dto.AuthRequest;
import com.example.newswebapp.dto.AuthResponse;
import com.example.newswebapp.dto.RegisterRequest;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) throws MessagingException {
        service.signup(request);
        return ResponseEntity.accepted().build();

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
        @GetMapping("/activate-account")
    public void confirm(
            @RequestParam String token
    ) throws MessagingException {
        service.activateAccount(token);
    }
    // @PostMapping("/logout")
    //     public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
    //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //     if (auth != null) {
    //         new SecurityContextLogoutHandler().logout(request, response, auth);
    //     }
    //     return ResponseEntity.ok().build();
    // }
    }
    
    

