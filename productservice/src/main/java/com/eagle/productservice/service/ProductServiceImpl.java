package com.eagle.productservice.service;

import com.eagle.productservice.entity.Product;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        return null;
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public void deleteProduct(int id) {

    }
}
