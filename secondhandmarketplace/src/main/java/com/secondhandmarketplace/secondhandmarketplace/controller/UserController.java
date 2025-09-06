package com.secondhandmarketplace.secondhandmarketplace.controller;


import com.secondhandmarketplace.secondhandmarketplace.dto.UserDtos.*;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import com.secondhandmarketplace.secondhandmarketplace.service.UserService;
import com.secondhandmarketplace.secondhandmarketplace.util.RequestUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<MeResponse> me(HttpServletRequest req) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        return ResponseEntity.ok(userService.me(u));
    }

    @PutMapping("/me")
    public ResponseEntity<MeResponse> update(HttpServletRequest req, @Valid @RequestBody UpdateProfileRequest body) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        return ResponseEntity.ok(userService.update(u, body));
    }
}
