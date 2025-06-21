package com.ashokit.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.dtos.ProductDto;

@FeignClient(name = "ProductService")
public interface ProductClient {
       
	@GetMapping("/products/{id}")
	public ProductDto getProductById(@RequestParam Long productId);
}
