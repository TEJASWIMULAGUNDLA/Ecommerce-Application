package com.eagle.productservice.rest;

import com.eagle.productservice.dto.ProductRequestDTO;
import com.eagle.productservice.dto.ProductResponseDTO;
import com.eagle.productservice.entity.Product;
import com.eagle.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/product-api")
public class ProductController {

    @Autowired
    private ProductService prodService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDTO> saveProduct(
            @RequestPart("productDTO") ProductRequestDTO productDTO,
            @RequestPart("image") MultipartFile imageFile) throws IOException {
        ProductResponseDTO product =prodService.createProduct(productDTO,imageFile);

           return ResponseEntity.ok(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        ProductResponseDTO dto = prodService.getProductById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/products/filter")
    public ResponseEntity<List<ProductResponseDTO>> filterProducts(
            @RequestParam String category,
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {

        List<ProductResponseDTO> products = prodService.getProductsByCategoryTreeAndPrice(category, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> viewAllProducts() {
        return ResponseEntity.ok(prodService.getAllProducts());
    }



}
