package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MongoTemplate template;

	@Override
	public List<User> getUsers() {
		return userDao.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}

	@Override
	public void deleteUser(String id) {
		userDao.deleteById(id);
	}

	@Override
	public void updateUser(User user) {
		userDao.findById(user.getId()).ifPresent(u -> {
			u.setName(user.getName());
			u.setAge(user.getAge());
			u.setDescription(user.getDescription());
			u.setUpdateDate(new Date());
			u.setReviser(user.getId());
			u.setAccount(user.getAccount());
			u.setPassword(user.getPassword());
			userDao.save(u);
		});
	}

	@Override
	public User getUser(String id) {
		return userDao.findById(id).get();
	}

	@Override
	public List<User> getUsers(User user) {
		ExampleMatcher matcher = ExampleMatcher.matching().
				withMatcher("name",GenericPropertyMatchers.contains())
				.withMatcher("sex", GenericPropertyMatchers.contains())
				.withMatcher("description", GenericPropertyMatchers.contains())
				.withIgnorePaths("id");
		Example<User> example = Example.of(user, matcher); 
		return userDao.findAll(example);
	}

	@Override
	public List<User> getUserPage(int currentPage, int pageSize, User user) {
		Query query = new Query();
	    Criteria criteria = new Criteria();
	    criteria.and("name").is(user.getName());
	    Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Direction.DESC, "age"));
	    List<User> users = template.find(query.with(pageable), User.class);
//	    userDao.findAll(example, pageable);
	    return users;
	}

}
