package com.city.service.impl;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.city.dao.ICategoryDao;
import com.city.dao.IItemDao;
import com.city.model.Category;
import com.city.model.Uploadinfo;
import com.city.service.IItemService;

@Service
public class ItemServiceImpl implements IItemService {
	@Resource
	private IItemDao itemDaoImpl;
	@Resource
	private ICategoryDao categoryImpl;

	@Override
	public Uploadinfo queryById(int id) {
		return itemDaoImpl.queryById(id);
	}

	@Override
	public List<Uploadinfo> queryAll() {
		return itemDaoImpl.queryAll();
	}

	@Override
	public void delete(Uploadinfo Uploadinfo) {
		// TODO Auto-generated method stub
		itemDaoImpl.delete(Uploadinfo);
	}

	@Override
	public void insert(Uploadinfo Uploadinfo) {
		// TODO 自动生成的方法存根
		itemDaoImpl.insert(Uploadinfo);
	}

	@Override
	public void update(Uploadinfo Uploadinfo) {
		// TODO 自动生成的方法存根
		itemDaoImpl.update(Uploadinfo);
	}
	
	@Override
	public List<Uploadinfo> totalPage(String type){
		// TODO Auto-generated method stub
		String sql;
		if("picture".equals(type)){
			 sql = "from Uploadinfo where picturepath1 <> '' and picturepath1 <> 'picture/meizhaopian.png' order by dataTime desc";
		}else{
			sql="from Uploadinfo order by dataTime desc";
		}
		Query query = itemDaoImpl.getQuery(sql);
		List<Uploadinfo> infolist = query.list();
		return infolist;
	}

	@Override
	public List<Uploadinfo> onePage(int pageNow, int pageSize,String type) {
		// TODO Auto-generated method stub
		String sql;
		if("picture".equals(type)){
			 sql = "from Uploadinfo where picturepath1 <> '' and picturepath1 <> 'picture/meizhaopian.png' order by dataTime desc";
			 
		}else{
			sql="from Uploadinfo order by dataTime desc";
		}
		Query query = itemDaoImpl.getQuery(sql);
		query.setFirstResult((pageNow - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Uploadinfo> infolist = query.list();
		return infolist;
	}

	@Override
	public List<Uploadinfo> searchInfos(String value) {
		// TODO Auto-generated method stub
/*		List datas = getSession().createQuery("from ApplyInfo where applyName like '%" + keyWord + "%' or name like '%"
				+ keyWord + "%' or tjSchool like '%" + keyWord + "%'");*/
		String hql = "from Uploadinfo where content like '%" + value + "%' or telephone like '%" + value + "%'";
		Query query = itemDaoImpl.getQuery(hql);
		List<Uploadinfo> infolist = query.list();
		return infolist;
	}

	@Override
	public List<Uploadinfo> dateRange(String start, String end) {
		// TODO Auto-generated method stub
		
		Date beginDate = java.sql.Date.valueOf(start);
		Date endDate = java.sql.Date.valueOf(end);
		String hql = "from Uploadinfo info where info.dataTime <:endDate and info.dataTime >=:beginDate";  
		Query query = itemDaoImpl.getQuery(hql);
		query.setDate("beginDate",beginDate);   
		query.setDate("endDate",endDate);
		List<Uploadinfo> infolist = query.list();
		return infolist;
	}

	@Override
	public List<Uploadinfo> queryClassfic(int id) {
		// TODO Auto-generated method stub
		Category category = categoryImpl.queryById(id);
		String[] strName = { "classfic" };
		Object[] strValue = { category };
		List<Uploadinfo> list= itemDaoImpl.queryByFields(strName,strValue);
		System.out.println(list.size());
		return list;
	}
}
