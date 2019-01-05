package com.example.springmicroservices.controllers;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springmicroservices.beans.User;
import com.example.springmicroservices.exception.UserNotFoundException;
import com.example.springmicroservices.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping(path = "/user/{userId}")
	public Resource getUserById(@PathVariable int userId) throws UserNotFoundException {
		User user =  userService.getUserById(userId);
		if(user == null) {
			throw new UserNotFoundException("Unvalid User");
		}
		// code to have links for others services
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("get-all-users"));
		return resource;
	}
	
	@PostMapping(path = "/user/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		 userService.addUser(user);
		 // code to print the last added result 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri();
		 return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/user/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteById(id);
	}
	
	
	 	
}
