package com.secondhandmarketplace.secondhandmarketplace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity @Table(name="products", indexes = {
        @Index(name="idx_products_title", columnList = "title"),
        @Index(name="idx_products_category", columnList = "category")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable=false)
    private String title;

    @Column(length=2000)
    private String description;

    @NotBlank @Column(nullable=false)
    private String category;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal price;

    @Column(nullable=false)
    private String imageUrl; // placeholder URL

    @ManyToOne(optional=false)
    private User seller;

    @Column(nullable=false)
    private Instant createdAt;
}
