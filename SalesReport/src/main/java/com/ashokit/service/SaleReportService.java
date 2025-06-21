package com.ashokit.service;

import java.time.LocalDate;
import java.util.List;

import com.ashokit.dtos.SaleReportDto;

public interface SaleReportService {
               
	SaleReportDto generateReport(LocalDate startDate, LocalDate endDate,String OrderStatus,String paymentMode);

    List<SaleReportDto> getAllReports();

    List<SaleReportDto> getReportsBetweenStartDateAndEndDate(LocalDate startDate, LocalDate endDate);

    List<SaleReportDto> getReportBasedOnStatus(String status);

    SaleReportDto getReportById(Long reportId);

    void deleteReport(Long reportId);
}
