package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.OrderItemDTO;
import com.example.entity.Orderitems;
import com.example.mapper.OrderItemsMapper;
import com.example.repo.OrderItemRepository;

@Service
public class OrderitemServiceImpl implements OrderItemService{
	  @Autowired
	    private OrderItemRepository orderItemRepository;
	    @Autowired
	    private OrderItemsMapper orderItemsMapper;

	    @Override
	    public OrderItemDTO createOrderItem(OrderItemDTO dto) {
	        Orderitems entity = orderItemsMapper.toEntity(dto);
	        Orderitems saved = orderItemRepository.save(entity);
	        return orderItemsMapper.toDTO(saved);
	    }

	    @Override
	    public OrderItemDTO getOrderItemById(Integer orderitemid) {
	        return orderItemRepository.findById(orderitemid)
	                .map(orderItemsMapper::toDTO)
	                .orElse(null);
	    }

	    @Override
	    public List<OrderItemDTO> getAllOrderItems() {
	        return orderItemRepository.findAll()
	                .stream()
	                .map(orderItemsMapper::toDTO)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public void deleteOrderItem(Integer orderitemid) {
	        orderItemRepository.deleteById(orderitemid);
	    }
}
