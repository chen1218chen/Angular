package com.city.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weixinuser")
public class WeixinUser implements java.io.Serializable {

	  private  Integer  id;  //用户的唯一标识 
	  private String  openid;  //用户的唯一标识 
	  private String  nickname;//用户昵称 
	  private Integer sex;// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 
	  private String  province;//用户个人资料填写的省份 
	  private String  city;//普通用户个人资料填写的城市 
	  private String  country;// 国家，如中国为CN 
	  private String  headimgurl;  // 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 
	  private String[]  privilege;// 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom） 
	  private String  unionid;// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制） 
	  private String access_token;

	/** default constructor */
	public WeixinUser() {
	}

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "openid", nullable = false, length = 150)
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "nickname", nullable = true, length = 50)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "sex", nullable = true, length = 50)
	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	

	@Column(name = "province", nullable = true, length = 150)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", nullable = true, length = 150)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country", nullable = true, length = 50)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "headimgurl", nullable = true, length = 150)
	public String getHeadimgurl() {
		return this.headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	@Column(name = "privilege", nullable = true, length = 150)
	public String[] getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(String[] privilege) {
		this.privilege = privilege;
	}

	@Column(name = "unionid", nullable = true, length = 150)
	public String getUnionid() {
		return this.unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Column(name = "access_token",nullable = false, length = 150)
	public String getAccess_token() {
		return this.access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

}