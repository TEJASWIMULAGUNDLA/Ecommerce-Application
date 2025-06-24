package com.eagle.productservice.controller;

import com.eagle.productservice.dto.ProductDto;
import com.eagle.productservice.dto.ProductRequestDTO;
import com.eagle.productservice.dto.ProductResponseDTO;
import com.eagle.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;


   @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ResponseEntity<ProductDto> updateProduct(
           @PathVariable Long id,
           @RequestPart(value = "image", required = false) MultipartFile imageFile,
           @RequestPart("productDTO") ProductDto updatedProductDto
           ) throws IOException {
    log.info("Updating product with ID: {}", id);
       ProductDto updated = productService.updateProduct(id, updatedProductDto, imageFile);
       log.info("Product updated successfully with prodId: {} | productName : {}", updated.getProdId(), updated.getProductName());
       return ResponseEntity.ok(updated);
   }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully with ID: " + id);
    }
    //===========================================vimlesh===========================================

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDTO> saveProduct(
            @RequestPart("productDTO") ProductRequestDTO productDTO,
            @RequestPart("image") MultipartFile imageFile) throws IOException {
        ProductResponseDTO product =productService.createProduct(productDTO,imageFile);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/view/by/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        ProductResponseDTO dto = productService.getProductById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponseDTO>> filterProducts(
            @RequestParam String category,
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {

        List<ProductResponseDTO> products = productService.getProductsByCategoryTreeAndPrice(category, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/view")
    public ResponseEntity<List<ProductResponseDTO>> viewAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
