package com.secondhandmarketplace.secondhandmarketplace.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name="session_tokens")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SessionToken {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String token;

    @ManyToOne(optional=false)
    private User user;

    @Column(nullable=false)
    private Instant createdAt;

    @Column(nullable=false)
    private Instant expiresAt;
}
