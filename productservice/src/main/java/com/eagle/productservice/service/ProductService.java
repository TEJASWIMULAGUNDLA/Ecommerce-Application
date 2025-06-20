package com.eagle.productservice.service;

import com.eagle.productservice.dto.ProductRequestDTO;
import com.eagle.productservice.dto.ProductResponseDTO;
import com.eagle.productservice.entity.Product;
import org.springframework.web.multipart.MultipartFile;
import com.eagle.productservice.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO productDTO, MultipartFile imageFile);
    ProductResponseDTO getProductById(Long id);
    List<ProductResponseDTO> getAllProducts();


    ProductDto updateProduct(Long id, ProductDto updatedProductDto);

    public List<ProductResponseDTO> getProductsByCategoryTreeAndPrice(String categoryName, double minPrice, double maxPrice);
    Page<ProductDto> getAllProducts(int page, int size, String sortBy);

    void deleteProduct(Long id);
}
