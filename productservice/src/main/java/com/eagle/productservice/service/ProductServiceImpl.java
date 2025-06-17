package com.eagle.productservice.service;

import com.eagle.productservice.entity.Product;
import com.eagle.productservice.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setGstPercentage(updatedProduct.getGstPercentage());
            existingProduct.setHsnCode(updatedProduct.getHsnCode());
            existingProduct.setImageUrl(updatedProduct.getImageUrl());
            existingProduct.setCategory(updatedProduct.getCategory());

            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with ID: " + id);
        }
    }

    @Override
    public void deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with ID: " + id);
        }
    }


}
