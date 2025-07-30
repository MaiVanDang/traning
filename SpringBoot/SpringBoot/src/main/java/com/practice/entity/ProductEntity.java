package com.practice.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private double importPrice;
    private double sellingPrice;
    private Integer stockQuantity;
    private String imageUrl;
    private Boolean isFeatured;

    @ManyToOne
    @JoinColumn(name = "category_id") // tên cột trong bảng products
    private CategoryEntity category;

    @Column(columnDefinition = "TEXT")
    private String  description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
