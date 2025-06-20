package com.eagle.productservice.rest;


import com.eagle.productservice.dto.ProductRequestDTO;
import com.eagle.productservice.dto.ProductResponseDTO;
import com.eagle.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveProduct() throws Exception {
        ProductRequestDTO requestDTO = new ProductRequestDTO();
        requestDTO.setProductName("Nike Shoes");
        requestDTO.setDescription("Latest");
        requestDTO.setPrice(9000.0);
        requestDTO.setHsnCode("8516");
        requestDTO.setGstPercentage(18.0);
        requestDTO.setCategoryName("Shoes");
        requestDTO.setParentCategoryName("Footwear");

        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setProductName("Nike Shoes");
        responseDTO.setCategoryName("Shoes");
        responseDTO.setParentCategoryName("Footwear");
        responseDTO.setImageUrl("http://image-url");

        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", "image/jpeg", "fake".getBytes());
        MockMultipartFile productDTO = new MockMultipartFile("productDTO", "", "application/json",
                objectMapper.writeValueAsBytes(requestDTO));

        Mockito.when(productService.createProduct(any(ProductRequestDTO.class), any())).thenReturn(responseDTO);

        mockMvc.perform(multipart("/product-api")
                        .file(image)
                        .file(productDTO)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Nike Shoes"))
                .andExpect(jsonPath("$.categoryName").value("Shoes"));
    }

    @Test
    void testGetProductById() throws Exception {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductName("Nike Shoes");
        dto.setCategoryName("Shoes");

        Mockito.when(productService.getProductById(1L)).thenReturn(dto);

        mockMvc.perform(get("/product-api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Nike Shoes"))
                .andExpect(jsonPath("$.categoryName").value("Shoes"));
    }

    @Test
    void testGetAllProducts() throws Exception {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductName("Nike Shoes");

        Mockito.when(productService.getAllProducts()).thenReturn(List.of(dto));

        mockMvc.perform(get("/product-api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName").value("Nike Shoes"));
    }

    @Test
    void testFilterProducts() throws Exception {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductName("Nike Shoes");

        Mockito.when(productService.getProductsByCategoryTreeAndPrice(eq("Shoes"), eq(5000.0), eq(10000.0)))
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/product-api/products/filter")
                        .param("category", "Shoes")
                        .param("minPrice", "5000")
                        .param("maxPrice", "10000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName").value("Nike Shoes"));
    }
}
