package com.secondhandmarketplace.secondhandmarketplace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity @Table(name="users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Email @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String passwordHash;

    @NotBlank
    @Column(nullable=false)
    private String username;
}
