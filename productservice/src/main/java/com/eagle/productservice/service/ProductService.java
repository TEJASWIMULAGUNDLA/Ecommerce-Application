package com.eagle.productservice.service;

import com.eagle.productservice.entity.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);
    Product updateProduct(int id, Product updatedProduct);
    Product getProductById(int id);
    List<Product> getAllProducts();
    void deleteProduct(int id);
}