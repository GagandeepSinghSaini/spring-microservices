package com.example.springmicroservices.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmicroservices.beans.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping(path="/hello-world")
	public String helloWorldMessage() {
		return "hello world with no args";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBeanMessage() {
		return new HelloWorldBean("hello bean world");
	}
	
	@GetMapping(path = "/hello-world-path/{name}")
	public HelloWorldBean helloWorldBeanPathVriable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello, %s", name));
	}
	
	@GetMapping(value = "/user-internationalized")
	public String getMorningWishes(/*@RequestHeader(name = "Accept-Language", required = false) Locale locale*/) {
		//return messageSource.getMessage("message.morning.wishes", null, locale); // one way of doing, better way is below
		return messageSource.getMessage("message.morning.wishes", null, LocaleContextHolder.getLocale());
	}
	
}
