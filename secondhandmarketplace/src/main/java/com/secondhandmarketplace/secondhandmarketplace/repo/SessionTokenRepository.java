package com.secondhandmarketplace.secondhandmarketplace.repo;


import com.secondhandmarketplace.secondhandmarketplace.entity.SessionToken;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface SessionTokenRepository extends JpaRepository<SessionToken, Long> {
    Optional<SessionToken> findByToken(String token);
    long deleteByExpiresAtBefore(Instant cutoff);
    long deleteByUser(User user);

}
