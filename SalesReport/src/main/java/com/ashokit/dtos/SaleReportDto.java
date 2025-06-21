package com.ashokit.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;


@Data
public class SaleReportDto {
     
	
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
}
