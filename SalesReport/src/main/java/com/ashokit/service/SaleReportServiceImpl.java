package com.ashokit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.client.OrderClient;
import com.ashokit.client.ProductClient;
import com.ashokit.dtos.OrderDto;
import com.ashokit.dtos.OrderItemDto;
import com.ashokit.dtos.ProductDto;
import com.ashokit.dtos.SaleReportDto;
import com.ashokit.entity.SaleReport;
import com.ashokit.helper.Agg;
import com.ashokit.mapper.SalesReportMapper;
import com.ashokit.repository.SaleReportRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SaleReportServiceImpl implements SaleReportService {
	
	
	@Autowired
	SaleReportRepository salesReportRepository;
	
	@Autowired
	OrderClient orderClient;
	
	@Autowired
	ProductClient productClient;
	
	@Autowired
	SalesReportMapper mapper;
	

	@Override
	public SaleReportDto generateReport(LocalDate startDate,
	                                    LocalDate endDate,
	                                    String orderStatus,     // ← renamed for clarity
	                                    String paymentMode) {   // ← NEW filter
	    /* ------------------------------------------------------------------ *
	     * 1. Normalise parameters
	     * ------------------------------------------------------------------ */
	    if (endDate == null) {
	        endDate = startDate;
	    }

	    String statusFilter   = (orderStatus  != null && !orderStatus.trim().isEmpty())
	                            ? orderStatus.trim().toUpperCase()
	                            : null;

	    String paymentFilter  = (paymentMode  != null && !paymentMode.trim().isEmpty())
	                            ? paymentMode.trim().toUpperCase()
	                            : null;

	    /* ------------------------------------------------------------------ *
	     * 2. Fetch orders from Order‑service
	     * ------------------------------------------------------------------ *
	     * If your Order‑service already supports both filters, just add them
	     * to the request.  Otherwise pull the raw list and filter in‑memory
	     * as shown below.
	     */
	    List<OrderDto> orders = orderClient.getOrdersBetweenDates(startDate, endDate);
	                             // or: orderClient.getOrdersBetweenDates(startDate, endDate,
	                             //       statusFilter, paymentFilter);

	    /* ------------------------------------------------------------------ *
	     * 3. Apply filters locally (covers both “either” and “both” cases)
	     * ------------------------------------------------------------------ */
	    List<OrderDto> filteredOrders = orders.stream()
	        .filter(o -> statusFilter  == null || statusFilter.equals(o.getOrderStatus().toUpperCase()))
	        .filter(o -> paymentFilter == null || paymentFilter.equals(o.getPaymentMethod().toUpperCase()))
	        .toList();

	    /* ------------------------------------------------------------------ *
	     * 4. Aggregate KPIs – unchanged from your existing logic
	     * ------------------------------------------------------------------ */
	    int totalOrders = filteredOrders.size();

	    BigDecimal totalSales = filteredOrders.stream()
	        .map(OrderDto::getTotalAmount)
	        .reduce(BigDecimal.ZERO, BigDecimal::add);

	    List<OrderItemDto> allItems = filteredOrders.stream()
	        .flatMap(ord -> ord.getItems().stream())
	        .toList();

	    Map<Long, Agg> aggMap = new HashMap<>();
	    for (OrderItemDto item : allItems) {
	        Agg agg = aggMap.computeIfAbsent(item.getProductId(), k -> new Agg());
	        agg.unitsSold += item.getQuantity();
	        agg.revenue   = agg.revenue.add(item.getPrice()
	                             .multiply(BigDecimal.valueOf(item.getQuantity())));
	    }

	    Optional<Map.Entry<Long, Agg>> topEntryOpt = aggMap.entrySet().stream()
	        .max(Comparator.comparingInt(e -> e.getValue().unitsSold));

	    SaleReport report = new SaleReport();
	    if (topEntryOpt.isPresent()) {
	        Long topProductId = topEntryOpt.get().getKey();
	        Agg topAgg        = topEntryOpt.get().getValue();

	        ProductDto topProduct = productClient.getProductById(topProductId);

	        report.setTopProductId(topProductId);
	        report.setTopProductName(topProduct.getProductName());
	        report.setTopProductUnitsSold(topAgg.unitsSold);
	        report.setTopProductRevenue(topAgg.revenue);
	    }

	    int totalProductsSold = allItems.stream()
	        .mapToInt(OrderItemDto::getQuantity)
	        .sum();

	    BigDecimal averageOrderValue = totalOrders > 0
	        ? totalSales.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP)
	        : BigDecimal.ZERO;

	    /* ------------------------------------------------------------------ *
	     * 5. Populate & persist entity
	     * ------------------------------------------------------------------ */
	    String humanLabel = "Sales Report (" + startDate + " to " + endDate +
	                        (statusFilter  != null ? " / " + statusFilter  : "") +
	                        (paymentFilter != null ? " / " + paymentFilter : "") + ")";

	    report.setReportName(humanLabel);
	    report.setStartDate(startDate);
	    report.setEndDate(endDate);
	    report.setGeneratedAt(LocalDateTime.now());
	    report.setTotalOrders(totalOrders);
	    report.setTotalSales(totalSales);
	    report.setTotalProductsSold(totalProductsSold);
	    report.setAverageOrderValue(averageOrderValue);
	    report.setStatus("GENERATED");

	    SaleReport saved = salesReportRepository.save(report);
	    writeReportToExcel(saved);

	    return mapper.convertToDto(saved);
	}


	@Override
	public List<SaleReportDto> getAllReports() {
		List<SaleReport> all = salesReportRepository.findAll();
		                  
		 return all.stream()
	              .map(mapper::convertToDto)
	              .collect(Collectors.toList());
	}

	@Override
	public List<SaleReportDto> getReportsBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
		  
		if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
		    throw new IllegalArgumentException("Start date must be before end date");
		}
		
		
		List<SaleReport> reports = salesReportRepository.findByDateRange(startDate, endDate);
	    return reports.stream()
	                  .map(mapper::convertToDto)
	                  .collect(Collectors.toList());
		
		
		
		
	}

	@Override
	public List<SaleReportDto> getReportBasedOnStatus(String status) {
		 
		if(status==null) {
		    return  Collections.emptyList() ;    
		}
		                 
		List<SaleReport> byStatus = salesReportRepository.findByStatus(status);
	      return byStatus.stream().map(mapper::convertToDto)
	                 .collect(Collectors.toList());
	    
	}
	@Override
	public SaleReportDto getReportById(Long reportId) {
	    SaleReport report = salesReportRepository.findById(reportId)
	        .orElseThrow(() -> new EntityNotFoundException("SaleReport not found with ID: " + reportId));
	    
	    return mapper.convertToDto(report);
	}


	@Override
	public void deleteReport(Long reportId) {
		  SaleReport report = salesReportRepository.findById(reportId)
			        .orElseThrow(() -> new EntityNotFoundException("SaleReport not found with ID: " + reportId));
			    
			    salesReportRepository.delete(report);
	}
	
	

	public void writeReportToExcel(SaleReport report) {
		String filePath = "C:\\Users\\Sai Kumar\\OneDrive\\ドキュメント\\Desktop\\Excel\\SalesReport.xlsx";
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Sales Report");

	    // Header row
	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("Report Name");
	    header.createCell(1).setCellValue("Start Date");
	    header.createCell(2).setCellValue("End Date");
	    header.createCell(3).setCellValue("Total Orders");
	    header.createCell(4).setCellValue("Total Sales");
	    header.createCell(5).setCellValue("Total Products Sold");
	    header.createCell(6).setCellValue("Average Order Value");
	    header.createCell(7).setCellValue("Status");

	    // Data row
	    Row data = sheet.createRow(1);
	    data.createCell(0).setCellValue(report.getReportName());
	    data.createCell(1).setCellValue(report.getStartDate().toString());
	    data.createCell(2).setCellValue(report.getEndDate().toString());
	    data.createCell(3).setCellValue(report.getTotalOrders());
	    data.createCell(4).setCellValue(report.getTotalSales().doubleValue());
	    data.createCell(5).setCellValue(report.getTotalProductsSold());
	    data.createCell(6).setCellValue(report.getAverageOrderValue().doubleValue());
	    data.createCell(7).setCellValue(report.getStatus());
	    
	    
	    CellStyle headerStyle = workbook.createCellStyle();
	    Font font = workbook.createFont();
	    font.setBold(true);
	    headerStyle.setFont(font);
	    for (int i = 0; i <= 7; i++) {
	        header.getCell(i).setCellStyle(headerStyle);
	        sheet.autoSizeColumn(i);
	    }
	    
	    
	    File file = new File(filePath);
	    file.getParentFile().mkdirs();

	    try (FileOutputStream fos = new FileOutputStream(file)) {
	        workbook.write(fos);
	        workbook.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


}
