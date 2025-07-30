package com.practice.controller;

import com.practice.entity.ProductEntity;
import com.practice.service.ProductService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductEntity> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<ProductEntity> getById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductEntity create(@RequestBody ProductEntity product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public ProductEntity update(@PathVariable Integer id, @RequestBody ProductEntity product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }
}
