package com.eagle.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE products SET is_active = false WHERE id = ?")
@Where(clause = "is_active = true")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prod_id;

    private String productName;

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

    @Column(name = "is_active")
    private Boolean isActive =true;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;


}