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

import com.example.dto.OrderItemDTO;
import com.example.service.OrderItemService;
@RestController
@RequestMapping("/orderitems")
public class OrderItemcontroller {
	@Autowired
    private OrderItemService orderItemService;
	 @PostMapping("createorder")
	    public OrderItemDTO createOrderItem(@RequestBody OrderItemDTO dto) {
	        return orderItemService.createOrderItem(dto);
	    }

	    @GetMapping("/{orderitemid}")
	    public OrderItemDTO getOrderItem(@PathVariable Integer orderitemid) {
	        return orderItemService.getOrderItemById(orderitemid);
	    }

	    @GetMapping
	    public List<OrderItemDTO> getAllOrderItems() {
	        return orderItemService.getAllOrderItems();
	    }

	    @DeleteMapping("/{orderitemid}")
	    public void deleteOrderItem(@PathVariable Integer orderitemid) {
	        orderItemService.deleteOrderItem(orderitemid);
	    }
}
