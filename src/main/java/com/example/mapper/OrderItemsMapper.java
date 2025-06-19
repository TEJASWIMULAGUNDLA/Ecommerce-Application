package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.dto.OrderItemDTO;
import com.example.entity.Orderitems;

@Component
public class OrderItemsMapper {
	 public OrderItemDTO toDTO(Orderitems entity) {
	        OrderItemDTO dto = new OrderItemDTO();
	        dto.setOrderitemid(entity.getOrderitemid());
	        dto.setProductid(entity.getProductid());
	        dto.setQuantity(entity.getQuantity());
	        dto.setPrice(entity.getPrice());
	        return dto;
	    }

	    public Orderitems toEntity(OrderItemDTO dto) {
	        Orderitems entity = new Orderitems();
	        entity.setOrderitemid(dto.getOrderitemid());
	        entity.setProductid(dto.getProductid());
	        entity.setQuantity(dto.getQuantity());
	        entity.setPrice(dto.getPrice());
	        return entity;
	    }

}
