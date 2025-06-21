package com.example.service;

import java.util.List;

import com.example.entity.Orders;

public interface OrderService {
	 Orders createOrder(Orders order);
	    Orders getOrderById(Integer orderid);
	    List<Orders> getAllOrders();
	    void deleteOrder(Integer orderid);
}
