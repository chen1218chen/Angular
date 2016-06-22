package com.city.model;

import java.util.Date;
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
@Table(name = "lost")
public class Lost implements java.io.Serializable {

	// Fields

	private Integer id;
	private String goodName;
	private String people;
	private Date lostTime;
	private String place;
	private String imageurl;
	private String state;
	private String descption;
	// Constructors

	/** default constructor */
	public Lost() {
	}
	@Id
	@GeneratedValue
	@Column(name="id",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "goodname", nullable = false, length = 50)
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	
	@Column(name = "place", nullable = false, length = 50)
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	@Column(name = "state", nullable = false, length = 50)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "people", nullable = false, length = 20)
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	
	@Column(name = "lostTime", nullable = false, length = 10)
	public Date getLostTime() {
		return lostTime;
	}
	public void setLostTime(Date lostTime) {
		this.lostTime = lostTime;
	}
	
	@Column(name = "imageurl", nullable = false, length = 20)
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	@Column(name = "descption", nullable = false, length = 100)
	public String getDescption() {
		return descption;
	}
	public void setDescption(String descption) {
		this.descption = descption;
	}


}