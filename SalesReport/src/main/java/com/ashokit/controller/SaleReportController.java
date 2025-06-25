package com.ashokit.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.dtos.SaleReportDto;
import com.ashokit.service.SaleReportService;

@RestController
@RequestMapping("/sales-reports")
public class SaleReportController {
	
	@Autowired
	SaleReportService saleReportService;
	
	@GetMapping("/report/{startDate}/{endDate}/{status}/{paymentMode}")
	public ResponseEntity<SaleReportDto> generateReport(
	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	        @PathVariable String OrderStatus,
	        @PathVariable String paymentMode) {

	    SaleReportDto report = saleReportService.generateReport(startDate, endDate, OrderStatus,paymentMode);
	    return report != null 
	        ? ResponseEntity.ok(report)
	        : ResponseEntity.badRequest().build();
	}
     
	@GetMapping
	public ResponseEntity<List<SaleReportDto>> findAllReports() {
	    List<SaleReportDto> allReports = saleReportService.getAllReports();
	    
	    return allReports != null 
		        ? ResponseEntity.ok(allReports)
		        : ResponseEntity.badRequest().build();
	}
	
	
	@GetMapping("/range/{startDate}/{endDate}")
	public ResponseEntity<List<SaleReportDto>>findReportsBetweenDates( @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
		     
	 List<SaleReportDto> reportLists = saleReportService.getReportsBetweenStartDateAndEndDate(startDate, endDate);
	    
	 return reportLists != null 
		        ? ResponseEntity.ok(reportLists)
		        : ResponseEntity.badRequest().build();
	
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<SaleReportDto>> findReportsBasedOnStatus(@PathVariable String status){
		                            
	List<SaleReportDto> reportsStatus = saleReportService.getReportBasedOnStatus(status);
	return reportsStatus != null 
	        ? ResponseEntity.ok(reportsStatus)
	        : ResponseEntity.badRequest().build(); 
		           
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SaleReportDto> getReportByReportId(@PathVariable Long id){
		  SaleReportDto report = saleReportService.getReportById(id);
		  
		 return report !=null?ResponseEntity.ok(report):ResponseEntity.badRequest().build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReportByReportId(@PathVariable Long id) {
	    saleReportService.deleteReport(id);
	    return ResponseEntity.ok("SaleReport deleted successfully with ID: " + id);
	}

	


}
