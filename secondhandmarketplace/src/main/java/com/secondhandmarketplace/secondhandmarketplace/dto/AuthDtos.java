package com.secondhandmarketplace.secondhandmarketplace.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthDtos {
    public record RegisterRequest(
            @Email String email,
            @NotBlank String password,
            @NotBlank String username
    ) {}

    public record LoginRequest(
            @Email String email,
            @NotBlank String password
    ) {}

    public record AuthResponse(
            String token,
            Long userId,
            String email,
            String username
    ) {}
}
