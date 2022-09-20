package com.neusoft.elm.controller;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.po.User;

public class UserController {
	
	public Object getUserByIdByPass(HttpServletRequest request) throws Exception{
		 String userId = request.getParameter("userId");
		 String password = request.getParameter("password");
		 UserService service = new UserServiceImpl();
		 User user = service.getUserByIdByPass(userId, password);
		 return user;
	}
	
	public Object login(HttpServletRequest request) {
		String username = request.getParameter("username");
		System.out.println(username);
		User user = new User();
		user.setUserId("12345678");
		user.setUserName("zhangsan");
		return user;
	}
	
}
