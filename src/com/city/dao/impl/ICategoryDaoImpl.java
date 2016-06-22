package com.city.dao.impl;

import org.springframework.stereotype.Repository;
import com.city.dao.ICategoryDao;
import com.city.model.Category;

@Repository
public class ICategoryDaoImpl extends BaseDaoImpl<Category> implements ICategoryDao {
	public ICategoryDaoImpl() {
		super(Category.class);
	}

}
