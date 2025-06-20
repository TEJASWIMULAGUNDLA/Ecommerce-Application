package com.eagle.productservice.service;

import com.eagle.productservice.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductService {



    ProductDto updateProduct(Long id, ProductDto updatedProductDto);

    Page<ProductDto> getAllProducts(int page, int size, String sortBy);

    void deleteProduct(Long id);
}
