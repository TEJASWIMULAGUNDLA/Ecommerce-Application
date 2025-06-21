package com.example.feignsss;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.UserDto;

@FeignClient(name="USERSERVICE")
public interface UserService {
	@GetMapping("/users/{userid}")
UserDto getUserByid(@PathVariable("userid") Integer userid);
}
