package com.ashokit.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity

@Setter
@Getter
@Table(name = "SalesReport")
public class SaleReport {
	
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long reportId;

	  
	    private String reportName;

	
	    private LocalDate startDate;

	   
	    private LocalDate endDate;

	    
	    private LocalDateTime generatedAt;

	    
	    private Integer totalOrders;

	  
	    private BigDecimal totalSales;

	   
	    private Integer totalProductsSold;

	    
	    private BigDecimal averageOrderValue;

	  
	    private String status;
	    
	    private Long topProductId;

	    private String topProductName;

	    private Integer topProductUnitsSold;

	    private BigDecimal topProductRevenue;
	   
	    private String paymentMode;
}
