package com.secondhandmarketplace.secondhandmarketplace.service;


import com.secondhandmarketplace.secondhandmarketplace.dto.ProductDtos.*;
import com.secondhandmarketplace.secondhandmarketplace.entity.Product;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import com.secondhandmarketplace.secondhandmarketplace.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;

    public ProductDetail create(User seller, ProductCreateUpdateRequest req) {
        Product p = productRepo.save(Product.builder()
                .title(req.title())
                .description(req.description())
                .category(req.category())
                .price(req.price())
                .imageUrl(req.imageUrl())
                .seller(seller)
                .createdAt(Instant.now())
                .build());
        return mapDetail(p);
    }

    public ProductDetail update(User seller, Long id, ProductCreateUpdateRequest req) {
        Product p = productRepo.findById(id).orElseThrow();
        if (!p.getSeller().getId().equals(seller.getId())) throw new RuntimeException("Not your listing");
        p.setTitle(req.title());
        p.setDescription(req.description());
        p.setCategory(req.category());
        p.setPrice(req.price());
        p.setImageUrl(req.imageUrl());
        productRepo.save(p);
        return mapDetail(p);
    }

    public void delete(User seller, Long id) {
        Product p = productRepo.findById(id).orElseThrow();
        if (!p.getSeller().getId().equals(seller.getId())) throw new RuntimeException("Not your listing");
        productRepo.delete(p);
    }

    public ProductDetail get(Long id) {
        return mapDetail(productRepo.findById(id).orElseThrow());
    }

    public Page<ProductSummary> search(String q, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Product> res;
        if (q != null && !q.isBlank() && category != null && !category.isBlank()) {
            res = productRepo.findByTitleContainingIgnoreCaseAndCategoryIgnoreCase(q, category, pageable);
        } else if (q != null && !q.isBlank()) {
            res = productRepo.findByTitleContainingIgnoreCase(q, pageable);
        } else if (category != null && !category.isBlank()) {
            res = productRepo.findByCategoryIgnoreCase(category, pageable);
        } else {
            res = productRepo.findAll(pageable);
        }
        return res.map(this::mapSummary);
    }

    public Page<ProductSummary> myListings(User user, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return productRepo.findBySeller(user, pageable).map(this::mapSummary);
    }

    private ProductSummary mapSummary(Product p) {
        return new ProductSummary(p.getId(), p.getTitle(), p.getCategory(), p.getPrice(), p.getImageUrl(),
                p.getSeller().getId(), p.getSeller().getUsername());
    }

    private ProductDetail mapDetail(Product p) {
        return new ProductDetail(p.getId(), p.getTitle(), p.getDescription(), p.getCategory(), p.getPrice(),
                p.getImageUrl(), p.getSeller().getId(), p.getSeller().getUsername());
    }
}
