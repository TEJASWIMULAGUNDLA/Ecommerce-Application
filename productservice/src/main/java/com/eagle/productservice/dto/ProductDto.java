package com.eagle.productservice.dto;

import java.time.LocalDateTime;

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

    public ProductDto() {
    }

    public ProductDto(Long prodId, String productName, String description, double price,
                      double gstPercentage, String hsnCode, String imageUrl,
                      LocalDateTime createdAt, Long categoryId,
                      Boolean isActive, Integer stockQuantity) {
        this.prodId = prodId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.gstPercentage = gstPercentage;
        this.hsnCode = hsnCode;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
        this.isActive = isActive;
        this.stockQuantity = stockQuantity;
    }


    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getGstPercentage() {
        return gstPercentage;
    }

    public void setGstPercentage(double gstPercentage) {
        this.gstPercentage = gstPercentage;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
