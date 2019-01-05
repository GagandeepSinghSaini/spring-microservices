package com.example.springmicroservices.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.springmicroservices.beans.NewUser;
import com.example.springmicroservices.beans.Post;
import com.example.springmicroservices.beans.User;
import com.example.springmicroservices.exception.UserNotFoundException;
import com.example.springmicroservices.repository.PostRepository;
import com.example.springmicroservices.repository.UserRespository;
import com.example.springmicroservices.service.UserService;

@RestController
public class JPAUserController {

	//@Autowired
	//private UserService userService;
	
	@Autowired
	private UserRespository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping(path = "/jpa/users")
	public List<User> getAllUsers() {
		//return userService.getAllUsers();
		return userRepository.findAll();
	}
	
	@GetMapping(path = "/jpa/user/{userId}")
	public Resource getUserById(@PathVariable int userId) throws UserNotFoundException {
		//User user =  userService.getUserById(userId);
		User user = userRepository.findById(userId).orElse(new User());
		if(user == null) {
			throw new UserNotFoundException("Unvalid User");
		}
		// code to have links for others services
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("get-all-users"));
		return resource;
	}
	
	@PostMapping(path = "/jpa/add/user")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		 //userService.addUser(user);
		System.out.println("gagan in add user: "+user);
		userRepository.save(user);
		 // code to print the last added result 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri();
		 return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/jpa/delete/user/{id}")
	public void deleteUser(@PathVariable int id) {
		//User user = userService.deleteById(id);
		userRepository.deleteById(id);
	}
	
	@GetMapping(path = "/jpa/user/{id}/posts")
	public List<Post> getPosts(@PathVariable int id) throws Exception {
		System.out.println("Getting posts for id: "+id);
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new Exception("user does not exists: "+id);
		}
		return userOptional.get().getPosts();
	}
	
	@PostMapping(path = "/jpa/add/post/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable int userId) 
			throws Exception {
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(!userOptional.isPresent()) {
			throw new Exception("user does not exist with id: "+userId);
		}
		
		User user = userOptional.get();
		System.out.println("User: "+user);
		post.setUser(user);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("").buildAndExpand(post.getPostId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	 	
}
