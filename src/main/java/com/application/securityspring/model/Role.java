package com.application.securityspring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="role")
@Data
public class Role extends BaseEntity{

	@Column(name="name")
	private String name;
	
	
	@ManyToMany(mappedBy="roles", fetch= FetchType.LAZY)
	private List<User> users;
	
	
}
