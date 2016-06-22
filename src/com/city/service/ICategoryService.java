package com.city.service;

import java.util.List;
import com.city.model.Category;

public interface ICategoryService {

	public List<Category> queryAll();

	public List<Category> queryByLevel(String level);

	public Category queryById(int cid);

	public List<Category> queryByParent(String parent);

}
