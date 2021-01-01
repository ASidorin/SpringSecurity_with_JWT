package com.application.securityspring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.securityspring.model.Role;
import com.application.securityspring.model.Status;
import com.application.securityspring.model.User;
import com.application.securityspring.repository.RoleRepository;
import com.application.securityspring.repository.UserRepository;
import com.application.securityspring.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder)  {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	public User register(User user) {
		Role roleUser = roleRepository.findByName("ROLE_USER");
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(roleUser);
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(userRoles);
		user.setStatus(Status.ACTIVE);
		
		User registeredUser = userRepository.save(user);
		
		log.info("IN register - user: {} successfully registered", registeredUser);
		return registeredUser;
	}

	@Override
	public List<User> getAll() {
		List<User> result = userRepository.findAll();
		log.info("IN getAll - {} users found", result.size());
		return result;
	}

	@Override
	public User findByUsername(String username) {
		User result = userRepository.findByUsername(username);
		log.info("IN findByUsermane - user {} found by username: {}", result, username);
		return result;
	}

	@Override
	public User findById(Long id) {
		User result = userRepository.findById(id).orElse(null);
		if(result == null) {
			log.warn("IN findById - no user found by id: {}", id);
			return null;
		}
		
		log.info("IN findById - user; {} found by id: {}", result);
		return result;
	}

	@Override
	public void delete(Long id) {
	userRepository.deleteById(id);
	log.info("IN delete - user with id: {} successfully deleted" );
		
	}
	
	

}
