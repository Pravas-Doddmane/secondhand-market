package com.secondhandmarketplace.secondhandmarketplace.controller;

import com.secondhandmarketplace.secondhandmarketplace.dto.CommonDtos.MessageResponse;
import com.secondhandmarketplace.secondhandmarketplace.entity.OrderItem;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import com.secondhandmarketplace.secondhandmarketplace.service.OrderService;
import com.secondhandmarketplace.secondhandmarketplace.util.RequestUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderItem>> list(HttpServletRequest req) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        return ResponseEntity.ok(orderService.list(u));
    }

    @PostMapping("/checkout")
    public ResponseEntity<MessageResponse> checkout(HttpServletRequest req) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        int items = orderService.checkout(u);
        return ResponseEntity.ok(new MessageResponse("Purchased " + items + " items"));
    }
}
