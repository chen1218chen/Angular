package com.city.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.city.util.DateDeserializer;
import com.city.util.DateSerializer;

/**
 * Uploadinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "uploadinfo")
public class Uploadinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String telephone;
	private String name;
	private String content;
	private String picturepath1;
	private String picturepath2;
	private String videopath1;
	private Date dataTime;
	private Date resPonseTime;
	private String lon;//经度
	private String lat;//纬度
	private String address;
	private String remessage;
	private Category classfic;
	private String wentidizhi;

	// Constructors

	/** default constructor */
	public Uploadinfo() {
	}

	/** full constructor */
	public Uploadinfo(String telephone, String name, String content,
			String picturepath1, String picturepath2, String videopath1, Date dataTime,
			String lat, String lon, String address,String remessage,String wentidizhi) {
		super();
		this.telephone = telephone;
		this.name = name;
		this.content = content;
		this.picturepath1 = picturepath1;
		this.picturepath2 = picturepath2;
		this.videopath1 = videopath1;
		this.dataTime = dataTime;
		this.lat = lat;
		this.lon = lon;
		this.address = address;
		this.remessage=remessage;
		this.wentidizhi=wentidizhi;
		 
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

	@Column(name = "telephone", length = 50)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "picturepath1", length = 200)
	public String getPicturepath1() {
		return this.picturepath1;
	}

	public void setPicturepath1(String picturepath1) {
		this.picturepath1 = picturepath1;
	}
	
	
	
	@Column(name = "picturepath2", length = 200)
	public String getPicturepath2() {
		return this.picturepath2;
	}

	public void setPicturepath2(String picturepath2) {
		this.picturepath2 = picturepath2;
	}
	
	

	@Column(name = "videopath1", length = 200)
	public String getVideopath1() {
		return this.videopath1;
	}

	public void setVideopath1(String videopath1) {
		this.videopath1 = videopath1;
	}

	@Column(name = "dataTime", length = 19)
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializer.class)
	public Date getDataTime() {
		return this.dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	@Column(name = "lon", length = 100)
	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	@Column(name = "lat", length = 100)
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "remessage", length = 200)
	public String getRemessage() {
		return this.remessage;
	}

	public void setRemessage(String remessage) {
		this.remessage = remessage;
	}
	
	@ManyToOne(targetEntity = Category.class, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "classfic")
	public Category getClassfic() {
		return classfic;
	}

	public void setClassfic(Category classfic) {
		this.classfic = classfic;
	}
	@Column(name = "wentidizhi", length = 200)
	public String getWentidizhi() {
		return this.wentidizhi;
	}

	public void setWentidizhi(String wentidizhi) {
		this.wentidizhi = wentidizhi;
	}
}