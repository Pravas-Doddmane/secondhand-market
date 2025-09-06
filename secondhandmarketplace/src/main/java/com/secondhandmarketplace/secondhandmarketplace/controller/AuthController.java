package com.secondhandmarketplace.secondhandmarketplace.controller;

import com.secondhandmarketplace.secondhandmarketplace.dto.AuthDtos.*;
import com.secondhandmarketplace.secondhandmarketplace.dto.MessageResponse;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import com.secondhandmarketplace.secondhandmarketplace.service.AuthService;
import com.secondhandmarketplace.secondhandmarketplace.util.RequestUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Health-check endpoint (useful to confirm mapping works)
    @GetMapping
    public ResponseEntity<String> helloAuth() {
        return ResponseEntity.ok("Auth service is up ✅ Use /auth/register or /auth/login");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }
    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(HttpServletRequest req) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        authService.logout(u);  // delegate to service
        return ResponseEntity.ok(new MessageResponse("Logged out"));
    }

}
