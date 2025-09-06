package com.secondhandmarketplace.secondhandmarketplace.repo;


import com.secondhandmarketplace.secondhandmarketplace.entity.CartItem;
import com.secondhandmarketplace.secondhandmarketplace.entity.Product;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndProduct(User user, Product product);
    long deleteByUser(User user);
}
