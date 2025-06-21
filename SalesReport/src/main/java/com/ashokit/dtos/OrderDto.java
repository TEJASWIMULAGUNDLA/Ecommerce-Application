package com.ashokit.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
       
	    
		private Long orderId;

	    private LocalDateTime orderDate;

	    private String orderStatus; 
	    
	    private String paymentMethod;

	    private BigDecimal totalAmount;

	    private List<OrderItemDto> items;
	
	  
}
