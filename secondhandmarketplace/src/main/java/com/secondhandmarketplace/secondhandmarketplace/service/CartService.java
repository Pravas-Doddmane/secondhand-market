package com.secondhandmarketplace.secondhandmarketplace.service;

import com.secondhandmarketplace.secondhandmarketplace.dto.CartDtos;
import com.secondhandmarketplace.secondhandmarketplace.dto.CommonDtos.IdResponse;
import com.secondhandmarketplace.secondhandmarketplace.entity.CartItem;
import com.secondhandmarketplace.secondhandmarketplace.entity.Product;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import com.secondhandmarketplace.secondhandmarketplace.repo.CartItemRepository;
import com.secondhandmarketplace.secondhandmarketplace.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartRepo;
    private final ProductRepository productRepo;

    public IdResponse add(User user, Long productId) {
        Product p = productRepo.findById(productId).orElseThrow();
        CartItem item = cartRepo.findByUserAndProduct(user, p)
                .orElse(CartItem.builder().user(user).product(p).quantity(0).build());
        item.setQuantity(item.getQuantity() + 1);
        cartRepo.save(item);
        return new IdResponse(item.getId());
    }

    public List<CartItem> list(User user) {
        return cartRepo.findByUser(user);
    }
    public List<CartDtos.CartItemDto> listDto(User user) {
        return cartRepo.findByUser(user).stream()
                .map(ci -> new CartDtos.CartItemDto(ci.getId(),
                        ci.getProduct().getId(),
                        ci.getProduct().getTitle(),
                        ci.getProduct().getPrice(),
                        ci.getQuantity(),
                        ci.getProduct().getImageUrl()))
                .toList();
    }



    public void remove(User user, Long cartItemId) {
        CartItem ci = cartRepo.findById(cartItemId).orElseThrow();
        if (!ci.getUser().getId().equals(user.getId())) throw new RuntimeException("Not your cart item");
        cartRepo.delete(ci);
    }
}
