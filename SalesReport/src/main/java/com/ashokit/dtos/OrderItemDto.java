package com.ashokit.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemDto {
       
	private Long productId;     
    private Integer quantity;   
    private BigDecimal price; 
    
    List<OrderItemDto> items;
	
}
