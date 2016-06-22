package com.city.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

/**
 * Roles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role")
public class Role implements java.io.Serializable {

	// Fields

	private Integer rid;
	private String rname;

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String rname) {
		this.rname = rname;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "rid", unique = true, nullable = false)
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Column(name = "rname", length = 20)
	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

}