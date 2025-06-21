package com.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SalesReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesReportApplication.class, args);
	}

}
