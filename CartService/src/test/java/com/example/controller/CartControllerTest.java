package com.example.controller;

import com.example.dto.CartItemDTO;
import com.example.dto.CartResponseDTO;
import com.example.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddToCart() throws Exception {
        CartItemDTO itemDTO = new CartItemDTO();
        itemDTO.setUserId(1);
        itemDTO.setProductId(101);
        itemDTO.setQuantity(2);

        CartResponseDTO mockResponse = new CartResponseDTO();
        mockResponse.setCartId(1);
        mockResponse.setUserId(1);
        mockResponse.setItems(Collections.singletonList(itemDTO));

        when(cartService.addToCart(eq(1), any(CartItemDTO.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":1,\"productId\":101,\"quantity\":2}"))
            .andDo(result -> System.out.println("Response JSON: " + result.getResponse().getContentAsString()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.userId").value(1))
            .andExpect(jsonPath("$.items[0].productId").value(101));
    }


    @Test
    public void testGetCartItems() throws Exception {
        CartResponseDTO mockResponse = new CartResponseDTO();
        mockResponse.setCartId(1);
        mockResponse.setUserId(1);
        mockResponse.setItems(Collections.emptyList());

        when(cartService.getCart(1)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cart")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartId").value(1));
    }

    @Test
    public void testUpdateCartItem() throws Exception {
        CartItemDTO item = new CartItemDTO();
        item.setProductId(101);
        item.setQuantity(5);
        item.setUserId(1);

        CartResponseDTO mockResponse = new CartResponseDTO();
        mockResponse.setCartId(1);
        mockResponse.setUserId(1);
        mockResponse.setItems(Collections.singletonList(item));

        when(cartService.updateCartItem(1, 101, 5)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/cart/update")
                .param("userId", "1")
                .param("productId", "101")
                .param("quantity", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].quantity").value(5));
    }

    @Test
    public void testRemoveItem() throws Exception {
        doNothing().when(cartService).removeCartItem(1, 101);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cart/delete")
                .param("userId", "1")
                .param("productId", "101"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cart item deleted successfully"));
    }

    @Test
    public void testClearCart() throws Exception {
        doNothing().when(cartService).clearCart(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cart/clear")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cart cleared successfully"));
    }
}
