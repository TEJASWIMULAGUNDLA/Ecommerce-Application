package com.eagle.productservice.service;

import com.eagle.productservice.dto.ProductDto;
import com.eagle.productservice.entity.Category;
import com.eagle.productservice.entity.Product;
import com.eagle.productservice.repo.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;






    @Override
    public ProductDto updateProduct(Long id, ProductDto updatedProductDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        existingProduct.setProductName(updatedProductDto.getProductName());
        existingProduct.setDescription(updatedProductDto.getDescription());
        existingProduct.setPrice(updatedProductDto.getPrice());
        existingProduct.setGstPercentage(updatedProductDto.getGstPercentage());
        existingProduct.setHsnCode(updatedProductDto.getHsnCode());
        existingProduct.setImageUrl(updatedProductDto.getImageUrl());
        existingProduct.setIsActive(updatedProductDto.getIsActive());
        existingProduct.setStockQuantity(updatedProductDto.getStockQuantity());


        if (updatedProductDto.getCategoryId() != null) {
            Category category = new Category();
            category.setCategory_id(updatedProductDto.getCategoryId());
            existingProduct.setCategory(category);
        }

        Product updated = productRepository.save(existingProduct);
        return mapToDto(updated);
    }


    @Override
    public Page<ProductDto> getAllProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(this::mapToDto);
    }


    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with ID: " + id);
        }
    }

    //Mapping Methods

    private ProductDto mapToDto(Product product) {
        return new ProductDto(
                product.getProd_id(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getGstPercentage(),
                product.getHsnCode(),
                product.getImageUrl(),
                product.getCreatedAt(),
                product.getCategory() != null ? product.getCategory().getCategory_id() : null,
                product.getIsActive(),
                product.getStockQuantity()
        );
    }

    private Product mapToEntity(ProductDto dto) {
        Product product = new Product();
        product.setProd_id(dto.getProdId());
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setGstPercentage(dto.getGstPercentage());
        product.setHsnCode(dto.getHsnCode());
        product.setImageUrl(dto.getImageUrl());
        product.setCreatedAt(dto.getCreatedAt()); // or use @PrePersist
        product.setIsActive(dto.getIsActive());
        product.setStockQuantity(dto.getStockQuantity());

        if (dto.getCategoryId() != null) {
            Category category = new Category();
            category.setCategory_id(dto.getCategoryId());
            product.setCategory(category);
        }

        return product;
    }
}
