package com.example.dto;

import java.util.List;

public class CartResponseDTO {

    private Integer cartId;
    private Integer userId; // ✅ Add this field
    private List<CartItemDTO> items;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() { // ✅ Add getter
        return userId;
    }

    public void setUserId(Integer userId) { // ✅ Add setter
        this.userId = userId;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }
}
