package com.secondhandmarketplace.secondhandmarketplace.controller;


import com.secondhandmarketplace.secondhandmarketplace.dto.ProductDtos.*;
import com.secondhandmarketplace.secondhandmarketplace.dto.CommonDtos.*;
import com.secondhandmarketplace.secondhandmarketplace.entity.User;
import com.secondhandmarketplace.secondhandmarketplace.service.ProductService;
import com.secondhandmarketplace.secondhandmarketplace.util.RequestUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // Public browse + search + filter
    @GetMapping
    public ResponseEntity<Page<ProductSummary>> browse(
            @RequestParam(required=false) String q,
            @RequestParam(required=false) String category,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="12") int size
    ) {
        return ResponseEntity.ok(productService.search(q, category, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetail> get(@PathVariable Long id) {
        return ResponseEntity.ok(productService.get(id));
    }

    // Protected (seller required)
    @PostMapping
    public ResponseEntity<ProductDetail> create(HttpServletRequest req, @Valid @RequestBody ProductCreateUpdateRequest body) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        return ResponseEntity.ok(productService.create(u, body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetail> update(HttpServletRequest req, @PathVariable Long id,
                                                @Valid @RequestBody ProductCreateUpdateRequest body) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        return ResponseEntity.ok(productService.update(u, id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(HttpServletRequest req, @PathVariable Long id) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        productService.delete(u, id);
        return ResponseEntity.ok(new MessageResponse("Deleted"));
    }

    @GetMapping("/me")
    public ResponseEntity<Page<ProductSummary>> myListings(HttpServletRequest req,
                                                           @RequestParam(defaultValue="0") int page,
                                                           @RequestParam(defaultValue="12") int size) {
        User u = (User) req.getAttribute(RequestUser.ATTR);
        return ResponseEntity.ok(productService.myListings(u, page, size));
    }
}

