package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/listAll")
	public List<User> findAll() {
		return userService.getUsers();
	}
	
	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/{id}")
	public User GetUser(@PathVariable("id") String id) {
		return userService.getUser(id);
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteUser(@PathVariable("id") String id) {
		userService.deleteUser(id);
		return true;
	}
	
	@PutMapping("/update")
	public boolean updateUser(@RequestBody User user) {
		userService.updateUser(user);
		return true;
	}
	
	@GetMapping("/list")
	public List<User> findList(@RequestBody User user) {
		return userService.getUsers(user);
	}
	
	@GetMapping("/page")
	public List<User> findList(int currentPage, int pageSize, User user) {
		return userService.getUserPage(currentPage, pageSize, user);
	}
	
}
