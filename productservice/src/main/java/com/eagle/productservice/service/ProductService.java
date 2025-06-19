package com.eagle.productservice.service;

import com.eagle.productservice.dto.ProductRequestDTO;
import com.eagle.productservice.dto.ProductResponseDTO;
import com.eagle.productservice.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO productDTO, MultipartFile imageFile);
    ProductResponseDTO getProductById(Long id);
    List<ProductResponseDTO> getAllProducts();


    public List<ProductResponseDTO> getProductsByCategoryTreeAndPrice(String categoryName, double minPrice, double maxPrice);
}