package com.city.service;

import java.util.List;

import com.city.model.Uploadinfo;

public interface IItemService {
	public Uploadinfo queryById(int id);

	public List<Uploadinfo> queryAll();
	
	public List<Uploadinfo> onePage(int pageNow, int pageSize,String type);
	
	public List<Uploadinfo> totalPage(String type);
	
	public List<Uploadinfo> searchInfos(String value);

	public void delete(Uploadinfo Uploadinfo);

	public void insert(Uploadinfo Uploadinfo);

	public void update(Uploadinfo Uploadinfo);

	public List<Uploadinfo> dateRange(String start, String end);

	public List<Uploadinfo> queryClassfic(int id);
}
