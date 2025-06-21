package com.ashokit.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ashokit.dtos.SaleReportDto;
import com.ashokit.entity.SaleReport;

@Component
public class SalesReportMapper {
          
	@Autowired
	ModelMapper mapper;
	 
	
	public SaleReportDto convertToDto(SaleReport report) {
		return mapper.map(report, SaleReportDto.class);
	}
	
	public SaleReport convertToEntity(SaleReportDto reportDto){
		return mapper.map(reportDto, SaleReport.class);
	}
	
}
