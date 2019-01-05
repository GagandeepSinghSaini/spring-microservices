package com.example.springmicroservices.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springmicroservices.beans.User;

@Service
public class UserService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;
	
	static {
		users.add(new User(1, "Jack", 20));
		users.add(new User(2, "Mack", 30));
		users.add(new User(3, "Roy", 40));
	}
	
	public User addUser(User user) {
		if(user.getUserId() == 0) {
			user.setUserId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User getUserById(int id) {
		for(User user : users) {
			if(user.getUserId() == id) {
				return user;
			}
		}
		return null;
	}

	public List<User> getAllUsers() {
		return users;
	}
	
	public User deleteById(int id) {
		Iterator it = users.iterator();
		while(it.hasNext()) {
			User user = (User) it.next();
			if(user.getUserId() == id) {
				it.remove();
				return user;
			}
		}
		return null;
	}
	
}
