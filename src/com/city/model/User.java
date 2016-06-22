package com.city.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tuser")
public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String telephone;
	private String password;
	private Set<Role> roles = new HashSet<Role>();

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String name, String telephone, String password) {
		this.name = name;
		this.telephone = telephone;
		this.password = password;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "telephone", nullable = false, length = 50)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	@ManyToMany(cascade={CascadeType.MERGE}, fetch=FetchType.EAGER)
	@JoinTable(name = "user_to_role",   
		joinColumns= { @JoinColumn(name = "user_id")},
		inverseJoinColumns ={@JoinColumn(name = "role_id")  
	})
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}