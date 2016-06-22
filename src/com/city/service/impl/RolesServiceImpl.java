package com.city.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.city.dao.IRolesDao;
import com.city.dao.IUserDao;
import com.city.model.Role;
import com.city.service.IRolesService;

@Service
public class RolesServiceImpl implements IRolesService {
	@Resource
	private IUserDao userDaoImpl;
	@Resource
	private IRolesDao rolesImpl;
	@Override
	public List<Role> queryAll() {
		// TODO 自动生成的方法存根
		return rolesImpl.queryAll();
	}

	

}
