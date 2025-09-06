package com.secondhandmarketplace.secondhandmarketplace.repo;



import com.secondhandmarketplace.secondhandmarketplace.entity.Product;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByTitleContainingIgnoreCase(String q, Pageable p);
    Page<Product> findByCategoryIgnoreCase(String category, Pageable p);
    Page<Product> findBySeller(User seller, Pageable p);
    Page<Product> findByTitleContainingIgnoreCaseAndCategoryIgnoreCase(String q, String category, Pageable p);
}
