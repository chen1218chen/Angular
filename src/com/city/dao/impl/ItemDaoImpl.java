package com.city.dao.impl;

import org.springframework.stereotype.Repository;
import com.city.dao.IItemDao;
import com.city.model.Uploadinfo;

@Repository
public class ItemDaoImpl extends BaseDaoImpl<Uploadinfo> implements IItemDao {
	
	public ItemDaoImpl() {
		super(Uploadinfo.class);
	}
}
