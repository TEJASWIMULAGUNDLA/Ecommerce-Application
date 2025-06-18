package com.example.demo.client;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.kafka.dtos.User;

@FeignClient(name = "User-Service")
public interface UserFeignClient {
	@GetMapping("/subscribed-users")
	public Set<User> subscribedUsers();

}
