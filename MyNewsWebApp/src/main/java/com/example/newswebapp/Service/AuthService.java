package com.example.newswebapp.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.newswebapp.dto.RegisterRequest;
import com.example.newswebapp.Model.User;
import com.example.newswebapp.Model.VerificationToken;
import com.example.newswebapp.repository.UserRepository;
import com.example.newswebapp.repository.VerificationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository UserRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository VerificationTokenRepository;

    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(LocalDateTime.now());
        user.setEnable(false);

        UserRepository.save(user);

    }

    private String generateVericationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        VerificationTokenRepository.save(verificationToken);
        return token;
        

    }



}
