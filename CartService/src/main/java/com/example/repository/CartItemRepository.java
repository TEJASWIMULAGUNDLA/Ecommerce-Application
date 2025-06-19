package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
