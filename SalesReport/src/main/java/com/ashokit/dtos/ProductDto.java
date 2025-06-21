package com.ashokit.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto {
     
	 	private Long productId;

	    private String productName;

	    private BigDecimal price;
	 

	  
}
