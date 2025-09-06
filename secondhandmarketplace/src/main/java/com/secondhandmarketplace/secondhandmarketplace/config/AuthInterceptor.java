package com.secondhandmarketplace.secondhandmarketplace.config;


import com.secondhandmarketplace.secondhandmarketplace.entity.SessionToken;
import com.secondhandmarketplace.secondhandmarketplace.repo.SessionTokenRepository;
import com.secondhandmarketplace.secondhandmarketplace.util.RequestUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final SessionTokenRepository tokenRepo;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        // Public endpoints (auth & public product browsing)
        String path = req.getRequestURI();
        if (path.startsWith("/auth") || (path.startsWith("/products") && "GET".equals(req.getMethod()))) {
            return true;
        }
        String token = req.getHeader("X-Auth-Token");
        if (token == null || token.isBlank()) {
            res.sendError(401, "Missing X-Auth-Token");
            return false;
        }
        Optional<SessionToken> sessionOpt = tokenRepo.findByToken(token);
        if (sessionOpt.isEmpty() || sessionOpt.get().getExpiresAt().isBefore(Instant.now())) {
            res.sendError(401, "Invalid/Expired token");
            return false;
        }
        req.setAttribute(RequestUser.ATTR, sessionOpt.get().getUser());
        return true;
    }
}
