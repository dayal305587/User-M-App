package com.BikkadIT.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.bindings.LoginForm;
import com.BikkadIT.service.UserServiceI;

@RestController
public class LoginController {
	@Autowired
	private UserServiceI userServiceI;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		String loginCheck = userServiceI.LoginCheck(loginForm);
		return loginCheck;
		
	}

}
