package com.eagle.productservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(length = 1000)
    private String description;

    private double price;

    @Column(name = "gst_percentage")
    private double gstPercentage;

    @Column(name = "hsn_code")
    private String hsnCode;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Product() {
    }

    public Product(int id, String name, String description, double price, double gstPercentage, String hsnCode, String imageUrl, LocalDateTime createdAt, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.gstPercentage = gstPercentage;
        this.hsnCode = hsnCode;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", gstPercentage=" + gstPercentage +
                ", hsnCode='" + hsnCode + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt=" + createdAt +
                ", category=" + category +
                '}';
    }
}