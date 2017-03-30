package com.example.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	
	@RequestMapping(value ="/hello", method = RequestMethod.GET)
	public String sayHello(){
		return "Hello";
	}
	
	
}
