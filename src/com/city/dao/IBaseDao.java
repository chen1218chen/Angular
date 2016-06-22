package com.city.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
public interface IBaseDao<T extends Serializable> {
	//增加
	public void insert(T t);
	//更新
	public void update(T t);
	//删除
	public void delete(T t);
	//增加或更新
	public T merge(T t);
	//根据id加载pojo
	public T queryById(int id);
	public T queryById(String id);
	//根据id懒加载pojo
	public T loadById(int id);
	public T loadById(String id);
	//获取全部
	public List<T> queryAll();
	//更加某些字段 获取集合
	public List<T> queryByFields(String fields[],Object values[]);
	//根据某些字段 获取集合(模糊查询)
	public List<T> queryByFieldsStr(String fields[],String values[]);
	public List<T> queryByFieldsStr(String fields, String values);
	//直接拼接SQL语句查询
	public List<T> queryBySQL(String sql);
	//根据id删除
	public void deleteById(int id);
	public void deleteById(String id);
	//查询某个字段的多个值的全部记录
	public List<T> queryByValueArray(String field,String values[]);
	//查询某字段两个值范围内的记录
	public List<T> queryByRange(String field,Object value1,Object value2);
	//根据pojo类获取的基础sql
	public StringBuffer getBaseSQL();
	//根据传入的字段名集创建返回结果sql
	public StringBuffer setOutFields(String[] outFields);
	//根据sql获取Query
	public Query getQuery(String hql);
	public Query getSQLQuery(String sql);
	public void clear();
}
