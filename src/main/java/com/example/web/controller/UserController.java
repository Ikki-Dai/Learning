package com.example.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.web.JsonResponse;
import com.example.web.StatusCode;

@RestController(value = "/user")
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	private JsonResponse jsonResponse;
	
	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public JsonResponse login(){
		
			//jsonResponse  = new JsonResponse();
			User user = new User("ikki", "123456");
			Map map = new HashMap();
			map.put("user", user);
			log.info("login#####################################");
		return new JsonResponse(StatusCode.Success,map);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CrossOrigin("*")
	public JsonResponse regist(User user){
		log.info(user.toString());
		Map map = new HashMap();
		map.put("regist", "regist Success");
		return new JsonResponse(StatusCode.Success,map);
	}
	
}
