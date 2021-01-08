package com.example.demo.service;

import java.util.List;
import org.springframework.data.domain.*;
import com.example.demo.pojo.User;

public interface UserService {

	public List<User> getUsers();
	
	public User saveUser(User user);
	
	public void deleteUser(String id);
	
	public void updateUser(User user);
	
	public User getUser(String id);
	
	public List<User> getUsers(User user);
	
	public List<User> getUserPage(int currentPage, int pageSize, User user);
}
