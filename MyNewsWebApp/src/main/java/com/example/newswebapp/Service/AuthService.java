package com.example.newswebapp.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.newswebapp.dto.AuthRequest;
import com.example.newswebapp.dto.AuthResponse;
import com.example.newswebapp.dto.RegisterRequest;
import com.example.newswebapp.Model.User;
import com.example.newswebapp.Model.VerificationToken;
import com.example.newswebapp.repository.RoleRepository;
import com.example.newswebapp.repository.UserRepository;
import com.example.newswebapp.repository.VerificationTokenRepository;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository VerificationTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final JwtService jwtService;
    @Value("${http://localhost:4200/activate-account}")
    private String activationUrl;

    public void signup(RegisterRequest request) throws MessagingException{
        var userRole = roleRepository.findByName("USER")
                // todo - better exception handling
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initiated"));
        var user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enable(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    public AuthResponse authenticate(AuthRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getUsername());

        var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void activateAccount(String token) throws MessagingException {
        VerificationToken savedToken = VerificationTokenRepository.findByToken(token)
                // todo exception has to be defined
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiryDate())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been send to the same email address");
        }

        var user = userRepository.findById(savedToken.getUser().getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnable(true);
        userRepository.save(user);

        savedToken.setValidatedDate(LocalDateTime.now());
        VerificationTokenRepository.save(savedToken);
    }

    private String generateAndSaveVericationToken(User user) {
        String generatedToken = generateActivationCode(6);
        var token = VerificationToken.builder()
                .token(generatedToken)
                .CreatedDate(LocalDateTime.now())
                .expiryDate(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        VerificationTokenRepository.save(token);

        return generatedToken;
    }
    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveVericationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getUsername(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
                );
    }
    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }

}