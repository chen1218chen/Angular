package com.city.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.city.dao.IWeixinUserDao;
import com.city.model.User;
import com.city.model.WeixinUser;
import com.city.service.IWeixinUserService;

@Service
public class WeixinUserServiceImpl implements IWeixinUserService {
	@Resource
	private IWeixinUserDao WeixinuserDaoImpl;

	@Override
	public String getWeixinUserName() {
		// TODO Auto-generated method stub
		return WeixinuserDaoImpl.queryById(1).getNickname();
	}

	@Override
	public WeixinUser queryByID(int id) {
		// TODO Auto-generated method stub
		WeixinUser WeixinmyUser = WeixinuserDaoImpl.queryById(id);
		return WeixinmyUser;
	}

	@Override
	public List<WeixinUser> queryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WeixinUser> queryAll() {
		// TODO Auto-generated method stub
		List<WeixinUser> WeixinUser = WeixinuserDaoImpl.queryAll();
		return WeixinUser;
	}

	@Override
	public void insert(WeixinUser WeixinUser) {
		// TODO Auto-generated method stub
		WeixinuserDaoImpl.insert(WeixinUser);
	}

	@Override
	public void update(WeixinUser WeixinUser) {
		// TODO Auto-generated method stub
		WeixinuserDaoImpl.update(WeixinUser);
	}

	@Override
	public void delete(WeixinUser WeixinUser) {
		// TODO Auto-generated method stub
		WeixinuserDaoImpl.delete(WeixinUser);
	}

	@Override
	public WeixinUser load(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WeixinUser> queryByOpenid(String openid) {
		// TODO Auto-generated method stub
		String[] strName = { "openid" };
		String[] strValue = { openid };
		List<WeixinUser> list = WeixinuserDaoImpl.queryByFieldsStr(strName, strValue);
		return list;
	}


}
