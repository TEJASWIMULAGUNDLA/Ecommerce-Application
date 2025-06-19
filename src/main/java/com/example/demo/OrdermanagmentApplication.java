package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@ComponentScan(basePackages= {"com.example.service","com.example.controller","com.example.mapper","com.example.dto"})
@EntityScan(basePackages="com.example.entity")
@EnableJpaRepositories(basePackages="com.example.repo")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.example.feignsss")
public class OrdermanagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdermanagmentApplication.class, args);
	}

}
