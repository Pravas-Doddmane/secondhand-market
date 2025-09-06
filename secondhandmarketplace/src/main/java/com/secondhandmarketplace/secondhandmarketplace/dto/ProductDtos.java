package com.secondhandmarketplace.secondhandmarketplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductDtos {
    public record ProductCreateUpdateRequest(
            @NotBlank String title,
            String description,
            @NotBlank String category,
            @NotNull BigDecimal price,
            @NotBlank String imageUrl
    ) {}

    public record ProductSummary(
            Long id, String title, String category, BigDecimal price, String imageUrl, Long sellerId, String sellerName
    ) {}

    public record ProductDetail(
            Long id, String title, String description, String category, BigDecimal price,
            String imageUrl, Long sellerId, String sellerName
    ) {}
}
