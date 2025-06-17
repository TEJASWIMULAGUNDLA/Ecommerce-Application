package com.eagle.productservice.service;

import com.eagle.productservice.entity.Product;

import java.util.List;

public interface ProductService {


    Product updateProduct(int id, Product updatedProduct);


    void deleteProduct(int id);
}