package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Orders;
import com.example.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Orders createOrder(@RequestBody Orders order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{orderid}")
    public Orders getOrder(@PathVariable Integer orderid) {
        return orderService.getOrderById(orderid);
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{orderid}")
    public void deleteOrder(@PathVariable Integer orderid) {
        orderService.deleteOrder(orderid);
    }
}
