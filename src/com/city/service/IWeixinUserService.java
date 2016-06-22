package com.city.service;

import java.util.List;
import com.city.model.Role;
import com.city.model.WeixinUser;

public interface IWeixinUserService {
	public String getWeixinUserName();
	public WeixinUser queryByID(int id);
	public List<WeixinUser> queryByName(String name);
	public List<WeixinUser> queryAll();
	public void insert(WeixinUser WeixinUser);
	public void update(WeixinUser WeixinUser);
	public void delete(WeixinUser WeixinUser);
	public WeixinUser load(int uid);
	public List<WeixinUser> queryByOpenid(String openid);
}
