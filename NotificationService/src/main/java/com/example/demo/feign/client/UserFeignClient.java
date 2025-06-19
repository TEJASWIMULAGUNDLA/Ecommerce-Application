package com.example.demo.feign.client;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.kafka.dto.User;


@FeignClient(name = "User-Service")
public interface UserFeignClient {
	@GetMapping("/subscribed-users")
	public Set<User> subscribedUsers();

}