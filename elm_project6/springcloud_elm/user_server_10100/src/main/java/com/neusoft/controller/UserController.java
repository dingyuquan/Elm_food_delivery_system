package com.neusoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.neusoft.po.CommonResult;
import com.neusoft.po.User;
import com.neusoft.service.UserService;


@CrossOrigin("*")
@RestController
@RequestMapping("/UserController")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/getUserByIdByPass/{userId}/{password}")
	public CommonResult<User> getUserByIdByPass(
			@PathVariable("userId") String userId,
			@PathVariable("password") String password) throws Exception {
		
		User param = new User();
		param.setUserId(userId);
		param.setPassword(password);
		User user = userService.getUserByIdByPass(param);
		return new CommonResult(200, "success", user);
	}
	
	@GetMapping("/getUserById/{userId}")
	public CommonResult<Integer> getUserById(@PathVariable("userId") String userId) throws Exception {
		int result = userService.getUserById(userId);
		return new CommonResult(200, "success", result);
	}
	
	@PostMapping("/saveUser/{userId}/{password}/{userName}/{userSex}")
	public CommonResult<Integer> saveUser(
			@PathVariable("userId") String userId,
			@PathVariable("password") String password,
			@PathVariable("userName") String userName,
			@PathVariable("userSex") Integer userSex
			) throws Exception {
		User param = new User();
		param.setUserId(userId);
		param.setPassword(password);
		param.setUserName(userName);
		param.setUserSex(userSex);
		
		int result = userService.saveUser(param);
		
		CommonResult<Integer> createWalletResult = restTemplate.getForObject("http://localhost:40100/VirtualWalletController/createWalletByUserId/" + userId, CommonResult.class);
		if(createWalletResult.getCode() == 200) {
			//do nothing
		}else {
			//熔断，返回错误代码
		}
		
		
		return new CommonResult(200, "success", result);
	}

}




