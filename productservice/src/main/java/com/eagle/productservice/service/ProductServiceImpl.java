package com.eagle.productservice.service;

import com.eagle.productservice.awsservice.S3Service;
import com.eagle.productservice.dto.ProductRequestDTO;
import com.eagle.productservice.dto.ProductResponseDTO;
import com.eagle.productservice.entity.Category;
import com.eagle.productservice.entity.Product;
import com.eagle.productservice.repo.CategoryRepository;
import com.eagle.productservice.repo.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
   private S3Service s3Service;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productDTO, MultipartFile imageFile) {
        // 1. Upload image to Cloudinary
        String imageUrl;
        try {
            //imageUrl = cloudinaryService.uploadFile(imageFile);
             imageUrl = s3Service.uploadFile(imageFile);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage(), e);
        }

        // 2. Get or create parent category (if provided)
        Category parentCategory;
        if (productDTO.getParentCategoryName() != null && !productDTO.getParentCategoryName().isBlank()) {
            parentCategory = categoryRepository.findByCategoryName(productDTO.getParentCategoryName())
                    .orElseGet(() -> {
                        Category newParent = new Category(productDTO.getParentCategoryName());
                        return categoryRepository.save(newParent);
                    });
        } else {
            parentCategory = null;
        }

        // 3. Get or create child category (the one assigned to the product)
        Category category = categoryRepository.findByCategoryName(productDTO.getCategoryName())
                .orElseGet(() -> {
                    Category newCategory = new Category(productDTO.getCategoryName());
                    newCategory.setParentCategoryName(parentCategory); // self-reference
                    return categoryRepository.save(newCategory);
                });

        // 4. Set all product fields
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setGstPercentage(productDTO.getGstPercentage());
        product.setHsnCode(productDTO.getHsnCode());
        product.setImageUrl(imageUrl);
        product.setCategory(category);

        Product prod= productRepository.save(product);
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        BeanUtils.copyProperties(prod,responseDTO);
        Category category1=prod.getCategory();
        responseDTO.setCategoryName(category1.getCategoryName());
        responseDTO.setParentCategoryName(category1.getParentCategoryName().getCategoryName());
        return responseDTO;
    }


    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setGstPercentage(product.getGstPercentage());
        dto.setHsnCode(product.getHsnCode());
        dto.setImageUrl(product.getImageUrl());
        dto.setCreatedAt(product.getCreatedAt());

        if (product.getCategory() != null) {
            dto.setCategoryName(product.getCategory().getCategoryName());

            if (product.getCategory().getParentCategoryName() != null) {
                dto.setParentCategoryName(product.getCategory().getParentCategoryName().getCategoryName());
            }
        }

        return dto;
    }



    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }




    @Override
    public List<ProductResponseDTO> getProductsByCategoryTreeAndPrice(String categoryName, double minPrice, double maxPrice) {
        Category parent = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Category> allCategories = new ArrayList<>();
        collectAllSubCategories(parent, allCategories);

        List<Product> productList = productRepository.findByCategoryInAndPriceBetween(allCategories, minPrice, maxPrice);

        return productList.stream()
                .map(this::mapToDTO)
                .toList();
    }



    // helper method to collect all sub-categories recursively
 private void collectAllSubCategories(Category parent, List<Category> all) {
     all.add(parent);
     List<Category> children = categoryRepository.findByParentCategoryName(parent);
     for (Category child : children) {
         collectAllSubCategories(child, all);
     }
 }

//Mapping method to convert Product entity to ProductResponseDTO
    private ProductResponseDTO mapToDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setGstPercentage(product.getGstPercentage());
        dto.setHsnCode(product.getHsnCode());
        dto.setImageUrl(product.getImageUrl());
        dto.setCreatedAt(product.getCreatedAt());

        Category category = product.getCategory();
        if (category != null) {
            dto.setCategoryName(category.getCategoryName());
            if (category.getParentCategoryName() != null) {
                dto.setParentCategoryName(category.getParentCategoryName().getCategoryName());
            }
        }

        return dto;
    }


}
