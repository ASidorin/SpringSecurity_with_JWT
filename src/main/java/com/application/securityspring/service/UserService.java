package com.application.securityspring.service;

import java.util.List;

import com.application.securityspring.model.User;

public interface UserService {
	
	User register(User user);
	
	List<User> getAll();
	
	User findByUsername(String username);
	
	User findById(Long id);
	
	void delete(Long id);

}
