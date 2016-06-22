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
@Table(name = "category")
public class Category implements java.io.Serializable {

	// Fields

	private Integer cid;
	private String cname;
	private String level;
	private String parent;

	// Constructors

	/** default constructor */
	public Category() {
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "cid", unique = true, nullable = false)
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Column(name = "cname", length = 20)
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "level", length = 5)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	@Column(name = "parent", length = 5)
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

}