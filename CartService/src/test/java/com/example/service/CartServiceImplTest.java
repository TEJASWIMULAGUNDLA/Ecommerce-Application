package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dto.CartItemDTO;
import com.example.dto.CartResponseDTO;
import com.example.entity.Cart;
import com.example.entity.CartItem;
import com.example.repository.CartItemRepository;
import com.example.repository.CartRepository;

@SpringBootTest
public class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepo;

    @Mock
    private CartItemRepository itemRepo;

    private Cart mockCart;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockCart = new Cart();
        mockCart.setId(1);
        mockCart.setUserId(1);
        mockCart.setCreatedAt(LocalDateTime.now());
        mockCart.setUpdatedAt(LocalDateTime.now());
        mockCart.setItems(new ArrayList<>());
    }

    @Test
    public void testAddToCart_NewItem() {
        // Arrange
        CartItemDTO itemDTO = new CartItemDTO();
        itemDTO.setProductId(101);
        itemDTO.setQuantity(2);

        when(cartRepo.findByUserId(1)).thenReturn(Optional.of(mockCart));
        when(cartRepo.save(any(Cart.class))).thenReturn(mockCart);

        // Act
        CartResponseDTO response = cartService.addToCart(1, itemDTO);

        // Assert
        assertEquals(1, response.getItems().size());
        assertEquals(101, response.getItems().get(0).getProductId());
        assertEquals(2, response.getItems().get(0).getQuantity());
    }

    @Test
    public void testGetCart_WhenCartExists() {
        when(cartRepo.findByUserId(1)).thenReturn(Optional.of(mockCart));

        CartResponseDTO response = cartService.getCart(1);

        assertEquals(1, response.getCartId());
    }

    @Test
    public void testUpdateCartItem() {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(101);
        cartItem.setQuantity(1);
        mockCart.getItems().add(cartItem);

        when(cartRepo.findByUserId(1)).thenReturn(Optional.of(mockCart));
        when(cartRepo.save(any(Cart.class))).thenReturn(mockCart);

        CartResponseDTO response = cartService.updateCartItem(1, 101, 5);

        assertEquals(5, response.getItems().get(0).getQuantity());
    }

    @Test
    public void testRemoveCartItem() {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(101);
        cartItem.setQuantity(2);
        mockCart.getItems().add(cartItem);

        when(cartRepo.findByUserId(1)).thenReturn(Optional.of(mockCart));
        when(cartRepo.save(any(Cart.class))).thenReturn(mockCart);

        cartService.removeCartItem(1, 101);

        assertEquals(0, mockCart.getItems().size());
    }

    @Test
    public void testClearCart() {
        CartItem item1 = new CartItem();
        item1.setProductId(101);
        item1.setQuantity(2);
        mockCart.getItems().add(item1);

        CartItem item2 = new CartItem();
        item2.setProductId(102);
        item2.setQuantity(1);
        mockCart.getItems().add(item2);

        when(cartRepo.findByUserId(1)).thenReturn(Optional.of(mockCart));
        when(cartRepo.save(any(Cart.class))).thenReturn(mockCart);

        cartService.clearCart(1);

        assertEquals(0, mockCart.getItems().size());
    }

    @Test
    public void testGetOrCreateCart_NewCart() {
        Cart newCart = new Cart();
        newCart.setId(99);
        newCart.setUserId(2);
        newCart.setCreatedAt(LocalDateTime.now());
        newCart.setUpdatedAt(LocalDateTime.now());

        CartItem item = new CartItem();
        item.setProductId(111);
        item.setQuantity(1);
        item.setCart(newCart);
        newCart.setItems(List.of(item));

        when(cartRepo.findByUserId(2)).thenReturn(Optional.empty());
        when(cartRepo.save(any(Cart.class))).thenReturn(newCart);

        CartResponseDTO response = cartService.getCart(2);

        assertEquals(1, response.getItems().size());
        assertEquals(2, response.getItems().get(0).getUserId());  // ✅ Will now work
    }

}
