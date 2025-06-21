package com.example.feignsss;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.ProductDto;

@FeignClient(name="PRODUCTSERVICE")
public interface Productservice {
	@GetMapping("/products/{productid}")
	ProductDto getProductbyId(@PathVariable("productid")Integer productid);
}