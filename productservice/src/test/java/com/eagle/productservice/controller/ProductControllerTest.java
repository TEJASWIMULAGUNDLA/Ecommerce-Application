/*
package com.eagle.productservice.controller;

import com.eagle.productservice.dto.ProductDto;
import com.eagle.productservice.dto.ProductRequestDTO;
import com.eagle.productservice.dto.ProductResponseDTO;
import com.eagle.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(ProductController.class)
//@ContextConfiguration(classes = {ProductController.class, ProductControllerTest.MockConfig.class})
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private ProductDto sampleProductDto;

    @InjectMocks
    private ProductController productController;

    @Mock
    private MockMvc mockMvc;

    @Spy
    private ProductService productService;

    @Mock
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

    @SneakyThrows
    @Test
    void testGetAllProducts()  {
        Page<ProductDto> page = new PageImpl<>(List.of(sampleProductDto), PageRequest.of(0, 10), 1);
        when(productService.getAllProducts(0, 10, "prod_id")).thenReturn(page);

        mockMvc.perform(get("/api/admin/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].productName").value("Test Product"));
    }

//    @TestConfiguration
//    static class MockConfig {
//        @Bean
//        public ProductService productService() {
//            return Mockito.mock(ProductService.class);
//        }
//    }

//    @WebMvcTest(ProductController.class)
//    public static class ProductControllerTest {
//
//        @InjectMocks
//        private ProductController productController;
//
//        @Mock
//        private MockMvc mockMvc;
//
//        @Spy
//        private ProductService productService;
//
//        @Mock
//        private ObjectMapper objectMapper;

        @SneakyThrows
        @Test
        void testSaveProduct() {
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

            when(productService.createProduct(any(ProductRequestDTO.class), any())).thenReturn(responseDTO);

            mockMvc.perform(multipart("/api/admin/products")
                            .file(image)
                            .file(productDTO)
                            .contentType(MediaType.MULTIPART_FORM_DATA))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.productName").value("Nike Shoes"))
                    .andExpect(jsonPath("$.categoryName").value("Shoes"));
        }

        @SneakyThrows
        @Test
        void testGetProductById() {
            ProductResponseDTO dto = new ProductResponseDTO();
            dto.setProductName("Nike Shoes");
            dto.setCategoryName("Shoes");

            when(productService.getProductById(1L)).thenReturn(dto);

            mockMvc.perform(get("/product-api/products/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.productName").value("Nike Shoes"))
                    .andExpect(jsonPath("$.categoryName").value("Shoes"));
        }

//        @SneakyThrows
//        @Test
//        void testGetAllProducts() {
//            ProductResponseDTO dto = new ProductResponseDTO();
//            dto.setProductName("Nike Shoes");
//
//            when(productService.getAllProducts()).thenReturn(List.of(dto));
//
//            mockMvc.perform(get("/product-api/products"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$[0].productName").value("Nike Shoes"));
//        }

        @SneakyThrows
        @Test
        void testFilterProducts() {
            ProductResponseDTO dto = new ProductResponseDTO();
            dto.setProductName("Nike Shoes");

            when(productService.getProductsByCategoryTreeAndPrice(eq("Shoes"), eq(5000.0), eq(10000.0)))
                    .thenReturn(List.of(dto));

            mockMvc.perform(get("/product-api/products/filter")
                            .param("category", "Shoes")
                            .param("minPrice", "5000")
                            .param("maxPrice", "10000"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].productName").value("Nike Shoes"));
        }
    }
*/
//==========================================================================new from chatgpt==========================================================================
package com.eagle.productservice.controller;

import com.eagle.productservice.dto.ProductDto;
import com.eagle.productservice.dto.ProductRequestDTO;
import com.eagle.productservice.dto.ProductResponseDTO;
import com.eagle.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
void testUpdateProductWithImage() throws Exception {
    // Prepare the mock updated product
    ProductDto updatedDto = new ProductDto();
    updatedDto.setProdId(1L);
    updatedDto.setProductName("Updated Product");
    updatedDto.setDescription("Updated Desc");
    updatedDto.setPrice(1999.0);
    updatedDto.setGstPercentage(12);
    updatedDto.setHsnCode("5678");
    updatedDto.setImageUrl("http://updated-image.jpg");
    updatedDto.setIsActive(false);
    updatedDto.setStockQuantity(25);
    updatedDto.setCategoryId(3L);

    // Convert the DTO to JSON as a multipart file
    MockMultipartFile productDTO = new MockMultipartFile(
            "productDTO", "", "application/json", objectMapper.writeValueAsBytes(updatedDto));

    // Mock image file
    MockMultipartFile image = new MockMultipartFile(
            "image", "updated.jpg", "image/jpeg", "image-bytes".getBytes());

    // Mock service behavior
    when(productService.updateProduct(eq(1L), any(ProductDto.class), any(MultipartFile.class)))
            .thenReturn(updatedDto);

    // Perform the multipart PUT request (using PUT override)
    mockMvc.perform(multipart("/api/product/update/1")
                    .file(productDTO)
                    .file(image)
                    .with(request -> { request.setMethod("PUT"); return request; })
                    .contentType(MediaType.MULTIPART_FORM_DATA))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productName").value("Updated Product"))
            .andExpect(jsonPath("$.description").value("Updated Desc"));
 }

    @Test
    void testUpdateProductWithoutImage() throws Exception {
        ProductDto updatedDto = new ProductDto();
        updatedDto.setProdId(1L);
        updatedDto.setProductName("Updated Without Image");
        updatedDto.setDescription("No image provided");
        updatedDto.setPrice(1500.0);
        updatedDto.setCategoryId(2L);

        MockMultipartFile productDTO = new MockMultipartFile(
                "productDTO", "", "application/json", objectMapper.writeValueAsBytes(updatedDto));

        when(productService.updateProduct(eq(1L), any(ProductDto.class), isNull()))
                .thenReturn(updatedDto);

        mockMvc.perform(multipart("/api/product/update/1")
                        .file(productDTO)
                        .with(request -> { request.setMethod("PUT"); return request; })
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Updated Without Image"));
    }



    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/api/product/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product deleted successfully with ID: 1"));
    }


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

        when(productService.createProduct(any(), any())).thenReturn(responseDTO);

        mockMvc.perform(multipart("/api/product/add")
                        .file(productDTO)
                        .file(image)
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

        when(productService.getProductById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/product/view/by/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Nike Shoes"))
                .andExpect(jsonPath("$.categoryName").value("Shoes"));
    }

    @Test
    void testFilterProducts() throws Exception {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductName("Nike Shoes");

        when(productService.getProductsByCategoryTreeAndPrice("Shoes", 5000.0, 10000.0))
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/api/product/filter")
                        .param("category", "Shoes")
                        .param("minPrice", "5000")
                        .param("maxPrice", "10000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName").value("Nike Shoes"));
    }

    @Test
    void testViewAllProducts() throws Exception {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductName("Nike Shoes");

        when(productService.getAllProducts()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/product/view"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName").value("Nike Shoes"));
    }
}
