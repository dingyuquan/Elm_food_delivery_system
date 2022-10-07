package com.neusoft.elmboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@RestController
@RequestMapping("/UserController")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/getUserByIdByPass")
	public User getUserByIdByPass(User user) throws Exception {
		User tem2 = userService.getUserByIdByPass(user);
		System.out.println(tem2);
		return tem2;
	}
	
	@RequestMapping("/getUserById")
	public int getUserById(User user) throws Exception {
		return userService.getUserById(user.getUserId());
	}
	
	@RequestMapping("/saveUser")
	public int saveUser(User user) throws Exception {
		return userService.saveUser(user);
	}

}