package com.eagle.productservice.service;

import com.eagle.productservice.dto.ProductDto;
import com.eagle.productservice.entity.Category;
import com.eagle.productservice.entity.Product;
import com.eagle.productservice.repo.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product existingProduct;
    private ProductDto updatedDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        existingProduct = new Product();
        existingProduct.setProd_id(1L);
        existingProduct.setProductName("Old Product");
        existingProduct.setDescription("Old Desc");
        existingProduct.setPrice(500);
        existingProduct.setGstPercentage(18);
        existingProduct.setHsnCode("1234");
        existingProduct.setImageUrl("old.jpg");
        existingProduct.setCreatedAt(LocalDateTime.now());
        existingProduct.setIsActive(true);
        existingProduct.setStockQuantity(10);
        existingProduct.setCategory(new Category(2L, "Electronics", null));

        updatedDto = new ProductDto();
        updatedDto.setProdId(1L);
        updatedDto.setProductName("Updated Product");
        updatedDto.setDescription("Updated Desc");
        updatedDto.setPrice(1000);
        updatedDto.setGstPercentage(12);
        updatedDto.setHsnCode("5678");
        updatedDto.setImageUrl("new.jpg");
        updatedDto.setCreatedAt(LocalDateTime.now());
        updatedDto.setIsActive(false);
        updatedDto.setStockQuantity(5);
        updatedDto.setCategoryId(2L);
    }





    @Test
    void testUpdateProduct_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));


        Product updatedProduct = new Product();
        updatedProduct.setProd_id(1L);
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setDescription("Updated Desc");
        updatedProduct.setPrice(1000);
        updatedProduct.setGstPercentage(12);
        updatedProduct.setHsnCode("5678");
        updatedProduct.setImageUrl("new.jpg");
        updatedProduct.setCreatedAt(LocalDateTime.now());
        updatedProduct.setIsActive(false);
        updatedProduct.setStockQuantity(5);
        updatedProduct.setCategory(new Category(2L, "Electronics", null));

        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        ProductDto result = productService.updateProduct(1L, updatedDto);

        assertEquals("Updated Product", result.getProductName()); // ✅ FIXED
        verify(productRepository).save(any(Product.class));
    }


    @Test
    void testUpdateProduct_NotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productService.updateProduct(99L, updatedDto));

        assertEquals("Product not found with ID: 99", ex.getMessage());
    }

    @Test
    void testDeleteProduct_Success() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productService.deleteProduct(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void testUpdateProduct_WithNullCategoryId() {
        updatedDto.setCategoryId(null);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        ProductDto result = productService.updateProduct(1L, updatedDto);

        assertNotNull(result);
        verify(productRepository).save(any(Product.class));
    }




    @Test
    void testDeleteProduct_NotFound() {
        when(productRepository.existsById(99L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productService.deleteProduct(99L));

        assertEquals("Product not found with ID: 99", ex.getMessage());
    }

    @Test
    void testGetAllProducts() {
        Page<Product> page = new PageImpl<>(List.of(existingProduct));
        when(productRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<ProductDto> result = productService.getAllProducts(0, 10, "productName");

        assertEquals(1, result.getTotalElements());
        assertEquals("Old Product", result.getContent().get(0).getProductName());
    }
}
