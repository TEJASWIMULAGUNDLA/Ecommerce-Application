package com.ashokit.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.dtos.OrderDto;

@FeignClient(name ="Order-Service", url = "http://localhost:8081")
public interface OrderClient {
        
	  @GetMapping("/orders")
	    List<OrderDto> getOrdersBetweenDates(
	        @RequestParam("startDate") LocalDate startDate,
	        @RequestParam("endDate") LocalDate endDate
	       
	    );
}
