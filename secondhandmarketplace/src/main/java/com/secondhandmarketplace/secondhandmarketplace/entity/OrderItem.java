package com.secondhandmarketplace.secondhandmarketplace.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity @Table(name="order_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User user;

    @ManyToOne(optional=false)
    private Product product;

    @Column(nullable=false)
    private int quantity;

    @Column(nullable=false)
    private Instant purchasedAt;
}
