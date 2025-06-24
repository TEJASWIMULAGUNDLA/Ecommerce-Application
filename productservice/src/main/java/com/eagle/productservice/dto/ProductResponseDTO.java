package com.eagle.productservice.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProductResponseDTO {
    private String productName;
    private String description;
    private double price;
    private String hsnCode;
    private double gstPercentage;
    private String imageUrl;
    private String categoryName;
    private String parentCategoryName;
    private LocalDateTime createdAt;
    private Integer stockQuantity;
    private Boolean isActive;
}

