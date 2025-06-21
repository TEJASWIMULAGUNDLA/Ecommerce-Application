package com.example.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.OrderItemDTO;
import com.example.dto.OrdersDto;
import com.example.entity.Coupons;
import com.example.entity.Orderitems;
import com.example.entity.Orders;

@Component
public class OrderMapper {
	@Autowired
	private OrderItemsMapper orderItemsMapper;
	
    public OrderMapper(OrderItemsMapper orderItemsMapper) {
        this.orderItemsMapper = orderItemsMapper;
    }

    public OrdersDto toDTO(Orders entity) {
        OrdersDto dto = new OrdersDto();
        dto.setOrderid(entity.getOrderid());
        dto.setUserId(entity.getUserid());
        dto.setUserPermanentAddress(entity.getUserpermanentadrress());
        dto.setDeliveryAddress(entity.getDeliveryaddress());
        dto.setTotalAmount(entity.getTotalamount());
        dto.setCreatedAt(entity.getCreatedat());
        dto.setOrderstatus(entity.getOrderstatus());
        dto.setGst(entity.getGst());
        dto.setCartid(entity.getCartid());
        dto.setCoupon(entity.getCoupon());

        List<OrderItemDTO> itemDTOs = entity.getOrderItems()
            .stream()
            .map(orderItemsMapper::toDTO)
            .collect(Collectors.toList());
        dto.setOrderItems(itemDTOs);

        return dto;
    }

    public Orders toEntity(OrdersDto dto) {
        Orders entity = new Orders();
        entity.setOrderid(dto.getOrderid());
        entity.setUserid(dto.getUserid());
        entity.setUserpermanentadrress(dto.getUserPermanentAddress());
        entity.setDeliveryaddress(dto.getDeliveryAddress());
        entity.setTotalamount(dto.getTotalAmount());
        entity.setCreatedat(dto.getCreatedAt());
        entity.setOrderstatus(dto.getOrderstatus());
        entity.setGst(dto.getGst());
        entity.setCartid(dto.getCartid());
        entity.setCoupon(dto.getCoupon());

        List<Orderitems> items = dto.getOrderItems()
            .stream()
            .map(orderItemsMapper::toEntity)
            .peek(item -> item.setOrders(entity))  // Set back-reference
            .collect(Collectors.toList());
        entity.setOrderItems(items);

        return entity;
    }
}
