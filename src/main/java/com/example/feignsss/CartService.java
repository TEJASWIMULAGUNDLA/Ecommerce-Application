package com.example.feignsss;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.CartDto;

@FeignClient(name="CARTSERVICE")
public interface CartService {
	@GetMapping("/api/cart/user/{userid}")
	 ResponseEntity<CartDto> getCartByIdAndUserId(@PathVariable("userid") Integer userid);
	
	@DeleteMapping("/api/cart/clear/{userid}")
    ResponseEntity<String> clearCart(@PathVariable("userid") Integer userid);    
	
	
	}
