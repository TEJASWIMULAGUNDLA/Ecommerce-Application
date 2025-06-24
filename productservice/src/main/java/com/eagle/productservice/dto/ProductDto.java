package com.eagle.productservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {

    private Long prodId;
    private String productName;
    private String description;
    private double price;
    private double gstPercentage;
    private String hsnCode;
    private String imageUrl;
    private LocalDateTime createdAt;
    private Long categoryId; // Just using the ID for simplicity
    private Boolean isActive;
    private Integer stockQuantity;


}
