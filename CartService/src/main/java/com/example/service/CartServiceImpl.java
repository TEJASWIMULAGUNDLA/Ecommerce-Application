package com.example.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CartItemDTO;
import com.example.dto.CartResponseDTO;
import com.example.entity.Cart;
import com.example.entity.CartItem;
import com.example.repository.CartItemRepository;
import com.example.repository.CartRepository;
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository itemRepo;

    private Cart getOrCreateCart(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        return cartRepo.findByUserId(userId).orElseGet(() -> {
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setCreatedAt(LocalDateTime.now());
            cart.setUpdatedAt(LocalDateTime.now());
            return cartRepo.save(cart);
        });
    }

    @Override
    public CartResponseDTO addToCart(Integer userId, CartItemDTO itemDTO) {
        Cart cart = getOrCreateCart(userId);

        Optional<CartItem> existing = cart.getItems().stream()
            .filter(item -> item.getProductId().equals(itemDTO.getProductId()))
            .findFirst();

        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + itemDTO.getQuantity());
        } else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProductId(itemDTO.getProductId());
            item.setQuantity(itemDTO.getQuantity());
            cart.getItems().add(item);
        }

        cart.setUpdatedAt(LocalDateTime.now());
        cartRepo.save(cart);

        return toResponseDTO(cart);
    }

    @Override
    public CartResponseDTO getCart(Integer userId) {
        Cart cart = getOrCreateCart(userId);
        return toResponseDTO(cart);
    }

    @Override
    public CartResponseDTO updateCartItem(Integer userId, Integer productId, int quantity) {
        Cart cart = getOrCreateCart(userId);

        cart.getItems().forEach(item -> {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
            }
        });

        cart.setUpdatedAt(LocalDateTime.now());
        cartRepo.save(cart);
        return toResponseDTO(cart);
    }

    @Override
    public void removeCartItem(Integer userId, Integer productId) {
        Cart cart = getOrCreateCart(userId);
        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepo.save(cart);
    }

    @Override
    public void clearCart(Integer userId) {
        Cart cart = getOrCreateCart(userId);
        cart.getItems().clear();
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepo.save(cart);
    }

    private CartResponseDTO toResponseDTO(Cart cart) {
        CartResponseDTO dto = new CartResponseDTO();
        dto.setCartId(cart.getId());
        dto.setUserId(cart.getUserId());
        dto.setItems(
            cart.getItems().stream()
                .map(item -> {
                    CartItemDTO itemDTO = new CartItemDTO();
                    itemDTO.setProductId(item.getProductId());
                    itemDTO.setQuantity(item.getQuantity());
                    itemDTO.setUserId(cart.getUserId()); // Optional, but nice to have
                    return itemDTO;
                }).toList()
        );
        return dto;
    }


}

