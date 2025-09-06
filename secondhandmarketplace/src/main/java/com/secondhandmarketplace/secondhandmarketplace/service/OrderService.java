package com.secondhandmarketplace.secondhandmarketplace.service;



import com.secondhandmarketplace.secondhandmarketplace.entity.CartItem;
import com.secondhandmarketplace.secondhandmarketplace.entity.OrderItem;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import com.secondhandmarketplace.secondhandmarketplace.repo.CartItemRepository;
import com.secondhandmarketplace.secondhandmarketplace.repo.OrderItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartItemRepository cartRepo;
    private final OrderItemRepository orderRepo;

    public List<OrderItem> list(User user) {
        return orderRepo.findByUserOrderByPurchasedAtDesc(user);
    }

    @Transactional
    public int checkout(User user) {
        List<CartItem> cart = cartRepo.findByUser(user);
        for (CartItem ci : cart) {
            orderRepo.save(OrderItem.builder()
                    .user(user)
                    .product(ci.getProduct())
                    .quantity(ci.getQuantity())
                    .purchasedAt(Instant.now())
                    .build());
        }
        cartRepo.deleteByUser(user);
        return cart.size();
    }
}
