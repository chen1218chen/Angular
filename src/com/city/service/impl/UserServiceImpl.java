package com.city.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.city.dao.IRolesDao;
import com.city.dao.IUserDao;
import com.city.model.Role;
import com.city.model.User;
import com.city.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDaoImpl;
	@Resource
	private IRolesDao rolesImpl;

	@Override
	public String getUserName() {
		return userDaoImpl.queryById(1).getName();
	}

	@Override
	public List<User> queryAll() {
		List<User> userList = userDaoImpl.queryAll();
		return userList;
	}

	@Override
	public List<User> queryByName(String name) {
		String[] strName = { "uName" };
		String[] strValue = { name };
		List<User> list = userDaoImpl.queryByFieldsStr(strName, strValue);
		return list;
	}

	@Override
	public User queryByTel(String telephone) {
		String[] strName = { "telephone" };
		String[] strValue = { telephone };
		List<User> list = userDaoImpl.queryByFieldsStr(strName, strValue);
		return list.get(0);
	}
	
	@Override
	public User queryByID(int uid) {
		// TODO Auto-generated method stub
		User myUser = userDaoImpl.queryById(uid);
		return myUser;
	}

	@Override
	public User load(int uid) {
		User user = userDaoImpl.loadById(uid);
		return user;
		 
	}
	@Override
	public void insert(User user) {
		 userDaoImpl.insert(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDaoImpl.update(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		userDaoImpl.delete(user);
	}

	@Override
	public List<Role> getUserRoles(String userName) {
		// TODO 自动生成的方法存根
		List<Role> roleList = new ArrayList<Role>();
		List<User> userList = queryByName(userName);
		for(User user:userList){
			Set<Role> roles = user.getRoles() ;
			for(Role role:roles){
				roleList.add(role);
			}
		}
		return roleList;
	}

}
