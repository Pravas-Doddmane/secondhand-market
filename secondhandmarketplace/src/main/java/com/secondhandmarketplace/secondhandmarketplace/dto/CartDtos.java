package com.secondhandmarketplace.secondhandmarketplace.dto;

import java.math.BigDecimal;

public class CartDtos {
    public record CartItemDto(
            Long cartItemId,
            Long productId,
            String title,
            BigDecimal price,
            int quantity,
            String imageUrl
    ) {}
}

