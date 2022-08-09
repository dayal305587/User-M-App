package com.BikkadIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.BikkadIT.service.UserServiceI;

@Controller
public class UserController {
	
	@Autowired
	private UserServiceI userServiceI;

}
