package com.secondhandmarketplace.secondhandmarketplace.repo;

import com.secondhandmarketplace.secondhandmarketplace.entity.OrderItem;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByUserOrderByPurchasedAtDesc(User user);
}
