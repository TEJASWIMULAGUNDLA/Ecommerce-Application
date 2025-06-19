package com.example.service;

import java.util.List;

import com.example.dto.OrderItemDTO;

public interface OrderItemService {
	OrderItemDTO createOrderItem(OrderItemDTO dto);
    OrderItemDTO getOrderItemById(Integer ordeitemid);
    List<OrderItemDTO> getAllOrderItems();
    void deleteOrderItem(Integer orderitemid);

}
