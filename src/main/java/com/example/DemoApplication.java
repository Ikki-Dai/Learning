package com.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {
	
	private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
		logger.trace("this is trace log");
		logger.debug("this is DEBUG log");
		logger.info("this is INFO log");
		logger.warn("this is WARN log");
		logger.error("this is ERROR log");
		SpringApplication.run(DemoApplication.class, args);
	}
}
