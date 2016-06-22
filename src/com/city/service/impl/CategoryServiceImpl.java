package com.city.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.city.dao.ICategoryDao;
import com.city.model.Category;
import com.city.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Resource
	private ICategoryDao categoryImpl;
	
	@Override
	public List<Category> queryAll() {
		// TODO 自动生成的方法存根
		return categoryImpl.queryAll();
	}
	@Override
	public List<Category> queryByLevel(String level) {
		String[] strName = { "level" };
		String[] strValue = { level };
		List<Category> list = categoryImpl.queryByFieldsStr(strName, strValue);
		return list;
	}
	@Override
	public Category queryById(int cid) {
		// TODO Auto-generated method stub
		Category category = categoryImpl.queryById(cid);
		return category;
	}
	@Override
	public List<Category> queryByParent(String parent) {
		// TODO Auto-generated method stub
		String[] strName = { "parent" };
		String[] strValue = { parent };
		List<Category> list = categoryImpl.queryByFieldsStr(strName, strValue);
		return list;
	}

}
