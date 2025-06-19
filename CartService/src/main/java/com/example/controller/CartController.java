package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CartItemDTO;
import com.example.dto.CartResponseDTO;
import com.example.service.CartService;

@RestController
@RequestMapping("api/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	// Add product to cart
	@PostMapping("/add")
	public ResponseEntity<CartResponseDTO> addToCart(@RequestBody CartItemDTO itemDTO) {
	    CartResponseDTO response = cartService.addToCart(itemDTO.getUserId(), itemDTO);
	    return ResponseEntity.ok(response); 
	}

    
    // Get Cart By UserId
    
    @GetMapping
    public ResponseEntity<CartResponseDTO> getCartItems(@RequestParam Integer userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }
    
    //Update quantity of product
    @PutMapping("/update")
    public ResponseEntity<CartResponseDTO> updateCartItem(
            @RequestParam Integer userId,
            @RequestParam Integer productId,
            @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateCartItem(userId, productId, quantity));
    }
    
 // Remove Item From Cart
    @DeleteMapping("/delete")
    public ResponseEntity<String> removeItem(@RequestParam Integer userId, @RequestParam Integer productId) {
        cartService.removeCartItem(userId, productId);
        return new ResponseEntity<>("Cart item deleted successfully", HttpStatus.OK);
    }
    
    //Clear Cart ByUserId
    
    @DeleteMapping("/clear")
    public ResponseEntity<String> ClearCart(@RequestParam Integer userId){
    	cartService.clearCart(userId);
    	return new ResponseEntity<>("Cart cleared successfully", HttpStatus.OK);
    }
}
