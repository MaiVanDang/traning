package com.practice.service;

import com.practice.entity.ProductEntity;
import com.practice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> getById(Integer id) {
        return productRepository.findById(id);
    }

    public ProductEntity create(ProductEntity product) {
        return productRepository.save(product);
    }

    public ProductEntity update(Integer id, ProductEntity updatedProduct) {
        updatedProduct.setId(id);
        return productRepository.save(updatedProduct);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
