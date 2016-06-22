package com.city.dao.impl;
import org.springframework.stereotype.Repository;
import com.city.dao.IUserDao;
import com.city.model.User;
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{
	public UserDaoImpl() {
		super(User.class);
	}
}
