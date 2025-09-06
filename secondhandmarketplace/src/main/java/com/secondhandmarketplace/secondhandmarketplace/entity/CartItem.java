package com.secondhandmarketplace.secondhandmarketplace.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="cart_items",
        uniqueConstraints=@UniqueConstraint(columnNames={"user_id","product_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CartItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User user;

    @ManyToOne(optional=false)
    private Product product;

    @Column(nullable=false)
    private int quantity;
}
