package com.example.service;

import com.example.dto.CartItemDTO;
import com.example.dto.CartResponseDTO;

public interface CartService {

	CartResponseDTO addToCart(Integer userId, CartItemDTO itemDTO);
    CartResponseDTO getCart(Integer userId);
    CartResponseDTO updateCartItem(Integer userId, Integer productId, int quantity);
    void removeCartItem(Integer userId, Integer productId);
    void clearCart(Integer userId);
	 
}
