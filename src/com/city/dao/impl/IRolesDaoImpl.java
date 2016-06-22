package com.city.dao.impl;

import org.springframework.stereotype.Repository;
import com.city.dao.IRolesDao;
import com.city.model.Role;

@Repository
public class IRolesDaoImpl extends BaseDaoImpl<Role> implements IRolesDao {
	public IRolesDaoImpl() {
		super(Role.class);
	}

}
