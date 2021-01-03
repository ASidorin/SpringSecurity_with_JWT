package com.application.securityspring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.application.securityspring.model.Role;
import com.application.securityspring.model.Status;
import com.application.securityspring.model.User;

public final class JwtUserFactory {
	
	public JwtUserFactory() {
		
	}
	
	public static JwtUser create(User user) {
		return new JwtUser(
				   user.getId(),
				   user.getUsername(),
				   user.getFirstName(),
				   user.getLastName(),
				   user.getEmail(),
				   user.getPassword(),
				   mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
				   user.getStatus().equals(Status.ACTIVE),
				   user.getUpdated()
				);
	}

	private static Collection<? extends GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
		return userRoles.stream()
				.map(role ->
				      new SimpleGrantedAuthority(role.getName())
				      ).collect(Collectors.toList());
	}
	
	

}
