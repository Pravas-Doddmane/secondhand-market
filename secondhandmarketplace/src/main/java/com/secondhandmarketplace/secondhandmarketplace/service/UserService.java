package com.secondhandmarketplace.secondhandmarketplace.service;


import com.secondhandmarketplace.secondhandmarketplace.dto.UserDtos.*;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import com.secondhandmarketplace.secondhandmarketplace.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public MeResponse me(User u) {
        return new MeResponse(u.getId(), u.getEmail(), u.getUsername());
    }

    public MeResponse update(User u, UpdateProfileRequest req) {
        u.setUsername(req.username());
        userRepo.save(u);
        return me(u);
    }
}
