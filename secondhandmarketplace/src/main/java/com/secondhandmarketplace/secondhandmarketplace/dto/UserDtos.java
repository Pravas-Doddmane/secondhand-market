package com.secondhandmarketplace.secondhandmarketplace.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDtos {
    public record MeResponse(Long id, String email, String username) {}
    public record UpdateProfileRequest(@NotBlank String username) {}
}
