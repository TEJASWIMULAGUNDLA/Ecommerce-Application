package com.ashokit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ashokit.entity.SaleReport;
import com.ashokit.mapper.SalesReportMapper;
import com.ashokit.repository.SaleReportRepository;
import com.ashokit.service.SaleReportService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SalesReportApplicationTests {
	
    @MockBean                         // Spring replaces the real bean in the context
    private SaleReportRepository repo;

    @Autowired                        // Real service bean from Spring context
    private SaleReportService service;

    @Autowired                        // Real mapper bean (has ModelMapper inside)
    private SalesReportMapper reportMapper;


	

	@Test
	void contextLoads() {
	}
	
	@Test
	void getAllReportsTest() {
		
		SaleReport report = new SaleReport();
	     report.setReportId(1L);
	     report.setReportName("Report 1");
	     
	     
	     SaleReport report1 = new SaleReport();
	     report1.setReportId(2L);
	     report1.setReportName("Report 2");
	     
	     List<SaleReport> mockReports = Arrays.asList(report,report1);
	     Mockito.when(repo.findAll()).thenReturn(mockReports);
	     
	     List<SaleReport> result = service.getAllReports().stream()
	    		                     .map(dto->reportMapper.convertToEntity(dto))
	    		                     .collect(Collectors.toList());
	     
	     Assertions.assertEquals(2, result.size());
	     
	    Assertions. assertEquals("Report 1",result.get(0).getReportName());
	     
		
		
	}
	
	
	
	

}
