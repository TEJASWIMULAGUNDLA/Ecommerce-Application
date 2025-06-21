package com.ashokit.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Entity;
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

	    @NotNull
	    private String reportName;

	    @NotNull
	    private LocalDate startDate;

	    @NotNull
	    private LocalDate endDate;

	    @NotNull
	    private LocalDateTime generatedAt;

	    @NotNull
	    private Integer totalOrders;

	    @NotNull
	    private BigDecimal totalSales;

	    @NotNull
	    private Integer totalProductsSold;

	    @NotNull
	    private BigDecimal averageOrderValue;

	    @NotNull
	    private String status;
	    
	    private Long topProductId;

	    private String topProductName;

	    private Integer topProductUnitsSold;

	    private BigDecimal topProductRevenue;
}
