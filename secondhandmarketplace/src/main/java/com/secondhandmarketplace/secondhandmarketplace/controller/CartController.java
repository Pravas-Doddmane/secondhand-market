package com.secondhandmarketplace.secondhandmarketplace.controller;

import com.secondhandmarketplace.secondhandmarketplace.dto.CartDtos;
import com.secondhandmarketplace.secondhandmarketplace.dto.CommonDtos.IdResponse;
import com.secondhandmarketplace.secondhandmarketplace.dto.CommonDtos.MessageResponse;
import com.secondhandmarketplace.secondhandmarketplace.entity.CartItem;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import com.secondhandmarketplace.secondhandmarketplace.service.CartService;
import com.secondhandmarketplace.secondhandmarketplace.util.RequestUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDtos.CartItemDto>> list(HttpServletRequest req) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        return ResponseEntity.ok(cartService.listDto(u));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<IdResponse> add(HttpServletRequest req, @PathVariable Long productId) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        return ResponseEntity.ok(cartService.add(u, productId));
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<MessageResponse> remove(HttpServletRequest req, @PathVariable Long cartItemId) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        cartService.remove(u, cartItemId);
        return ResponseEntity.ok(new MessageResponse("Removed"));
    }
}
