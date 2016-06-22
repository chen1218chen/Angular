package com.city.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.city.dao.IBaseDao;
@Transactional
public  class BaseDaoImpl<T extends Serializable> implements IBaseDao<T> {

	protected Class<T> entityClass;

	public BaseDaoImpl(Class<T> classT) {
		this.entityClass = classT;
	}

	/*
	 * @Resource的作用相当于@Autowired， 只不过@Autowired按byType自动注入，而@Resource默认按
	 * byName自动注入罢了。
	 * 
	 * @Resource有两个属性是比较重要的，分是name和type，Spring将@Resource注解的name属性解析为bean的名字，
	 * 而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略
	 * ，而使用type属性时则使用byType自动注入策略。如果既不指定name也不指定type属性， 这时将通过反射机制使用byName自动注入策略。
	 * 推荐使用：@Resource注解在字段上，这样就不用写setter方法了，
	 * 并且这个注解是属于J2EE的，减少了与spring的耦合。这样代码看起就比较优雅。
	 */
	@Resource
	protected SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional(readOnly = false)
	public void insert(T t) {
		this.getSession().save(t);
		this.getSession().flush();
//		Serializable result = this.getSession().save(t);
//		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void update(T t) {
		this.getSession().update(t);
		this.getSession().flush();
	}
	
	@Override
	@Transactional(readOnly = false)
	public void clear() {
		this.getSession().clear();
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(T t) {
		this.getSession().delete(t);
		this.getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false)
	public T merge(T t) {
		return (T) this.getSession().merge(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public T queryById(int id) {
		return (T) this.getSession().get(this.entityClass, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public T queryById(String id) {
		return (T) this.getSession().get(this.entityClass, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public T loadById(int id) {
		return (T) getSession().load(this.entityClass, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public T loadById(String id) {
		return (T) this.getSession().load(this.entityClass, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<T> queryAll() {
		StringBuffer hql = new StringBuffer(" from ");
		hql.append(this.entityClass.getSimpleName());
		
		return this.getQuery(hql.toString()).list();
	}
	public Query getQueryByField(StringBuffer hql, String[] fields, Object[] values) {
		for (int i = 0; i < fields.length; i++) {
			String field = fields[i];
			Object value = values[i];
			hql.append(" and ").append(field);
			if (value == null) {
				hql.append(" is null");
			} else {
				hql.append(" =:").append(field);
			}
		}
		Query query = this.getQuery(hql.toString());
		for (int i = 0; i < values.length; i++) {
			Object value = values[i];
			if (value != null) {
				query.setParameter(fields[i], value);
			}
		}
		return query;
	}
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> queryByFields(String[] fields, Object[] values) {
		StringBuffer hql = getBaseSQL();
		return this.getQueryByField(hql, fields, values).list();
	}
	@Transactional(readOnly = false)
	@Override
	public void deleteById(int id) {
		T t = this.queryById(id);
		this.delete(t);
	}
	@Transactional(readOnly = false)
	@Override
	public void deleteById(String id) {
		T t = this.queryById(id);
		this.delete(t);
	}
	@Override
	public StringBuffer getBaseSQL() {
		return new StringBuffer("from ").append(this.entityClass.getSimpleName()).append(" where 1=1 ");
	}
	@Override
	public StringBuffer setOutFields(String[] outFields) {
		StringBuffer temp = new StringBuffer("select ");
		if (outFields == null || outFields.length == 0)
			return new StringBuffer();
		else {
			for (String field : outFields)
				temp.append(field +  ",");
			temp.setCharAt(temp.length() - 1, ' ');
		}
		return temp;
	}
	
	@Override
	public Query getQuery(String hql) {
		return this.getSession().createQuery(hql);
	}
	
	@Override
	public Query getSQLQuery(String sql) {
		// TODO 自动生成的方法存根
		return this.getSession().createSQLQuery(sql);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> queryByFieldsStr(String fields[], String values[]) {
		StringBuffer hql = getBaseSQL();
		for (int i = 0; i < fields.length; i++) {
			hql.append(" and ( ").append(fields[i]).append(" like '%").append(values[i]).append("%' or ").append(fields[i]).append(" is null) ");
		}
		Query query = this.getQuery(hql.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> queryByFieldsStr(String fields, String values) {
		StringBuffer hql = getBaseSQL();
		hql.append(" and ( ").append(fields).append(" like '%").append(values).append("%')");
		// append(values).append("%' or ").append(fields).append(" is null) ");
		Query query = this.getQuery(hql.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	// 通过范围查询
	public List<T> queryByRange(String field, Object value1, Object value2) {
		StringBuffer hql = getBaseSQL();
		hql.append(" and ( ").append(field).append(">=").append(value1 + ") ");
		hql.append(" and ( ").append(field).append("<=").append(value2 + ")");
		Query query = this.getQuery(hql.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> queryByValueArray(String field, String values[]) {
		StringBuffer hql = getBaseSQL();
		hql.append("and( ");
		for (int i = 0; i < values.length; i++) {
			String value = values[i];
			if (i < values.length - 1)
				hql.append(field + " = '" + value + "' or ");
			else
				hql.append(field + " = '" + value + "' )");
		}

		System.out.println(hql.toString());
		Query query = this.getQuery(hql.toString());
		return query.list();
	}
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> queryBySQL(String sql) {
		StringBuffer bf = this.getBaseSQL();
		bf.append(" where 1=1 ").append(sql);
		Query query = this.getQuery(bf.toString());
		return query.list();
	}

}
