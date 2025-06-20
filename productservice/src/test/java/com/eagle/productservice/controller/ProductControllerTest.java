package com.eagle.productservice.controller;

import com.eagle.productservice.dto.ProductDto;
import com.eagle.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = {ProductController.class, ProductControllerTest.MockConfig.class})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductDto sampleProductDto;

    @BeforeEach
    void setUp() {
        sampleProductDto = new ProductDto();
        sampleProductDto.setProdId(1L);
        sampleProductDto.setProductName("Test Product");
        sampleProductDto.setDescription("Test Desc");
        sampleProductDto.setPrice(999.0);
        sampleProductDto.setGstPercentage(18);
        sampleProductDto.setHsnCode("1234");
        sampleProductDto.setImageUrl("test.jpg");
        sampleProductDto.setIsActive(true);
        sampleProductDto.setStockQuantity(50);
        sampleProductDto.setCategoryId(2L);
    }



    @Test
    void testUpdateProduct() throws Exception {
        when(productService.updateProduct(eq(1L), any())).thenReturn(sampleProductDto);

        mockMvc.perform(put("/api/admin/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleProductDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Test Product"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/api/admin/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product deleted successfully with ID: 1"));
    }

    @Test
    void testGetAllProducts() throws Exception {
        Page<ProductDto> page = new PageImpl<>(List.of(sampleProductDto), PageRequest.of(0, 10), 1);
        when(productService.getAllProducts(0, 10, "prod_id")).thenReturn(page);

        mockMvc.perform(get("/api/admin/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].productName").value("Test Product"));
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        public ProductService productService() {
            return Mockito.mock(ProductService.class);
        }
    }
}
