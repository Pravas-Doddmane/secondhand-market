package com.secondhandmarketplace.secondhandmarketplace.service;

import com.secondhandmarketplace.secondhandmarketplace.dto.AuthDtos.*;
import com.secondhandmarketplace.secondhandmarketplace.entity.SessionToken;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import com.secondhandmarketplace.secondhandmarketplace.repo.SessionTokenRepository;
import com.secondhandmarketplace.secondhandmarketplace.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final SessionTokenRepository tokenRepo;

    public AuthResponse register(RegisterRequest req) {
        userRepo.findByEmail(req.email()).ifPresent(u -> { throw new RuntimeException("Email already registered"); });
        String hash = BCrypt.hashpw(req.password(), BCrypt.gensalt(10));
        User user = userRepo.save(User.builder()
                .email(req.email()).passwordHash(hash).username(req.username()).build());
        return issueToken(user);
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepo.findByEmail(req.email()).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!BCrypt.checkpw(req.password(), user.getPasswordHash())) throw new RuntimeException("Invalid credentials");
        return issueToken(user);
    }

    private AuthResponse issueToken(User user) {
        // Clean old tokens (optional)
        tokenRepo.deleteByExpiresAtBefore(Instant.now());
        SessionToken t = tokenRepo.save(SessionToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .createdAt(Instant.now())
                .expiresAt(Instant.now().plus(24, ChronoUnit.HOURS))
                .build());
        return new AuthResponse(t.getToken(), user.getId(), user.getEmail(), user.getUsername());
    }
    public void logout(User user) {
        tokenRepo.deleteByUser(user);
    }

}
