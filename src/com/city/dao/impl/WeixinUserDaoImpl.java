package com.city.dao.impl;
import org.springframework.stereotype.Repository;
import com.city.dao.IUserDao;
import com.city.dao.IWeixinUserDao;
import com.city.model.WeixinUser;
@Repository
public class WeixinUserDaoImpl extends BaseDaoImpl<WeixinUser> implements IWeixinUserDao{
	public WeixinUserDaoImpl() {
		super(WeixinUser.class);
	}
}
