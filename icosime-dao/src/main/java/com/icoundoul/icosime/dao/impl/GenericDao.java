package com.icoundoul.icosime.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.icoundoul.icosime.dao.ICOSIMESessionFactoryManager;
import com.icoundoul.icosime.dao.IGenericDao;

public class GenericDao implements IGenericDao{
	
	@Inject
	private ICOSIMESessionFactoryManager  icoSimeSessionFactoryManager;
	
	protected Session getSession(){
		return icoSimeSessionFactoryManager.session();
	}
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, Serializable id) {
		return (T) this.getSession().get(clazz, id);
	}

	public <T> List<T> getList(Class<T> clazz) {
		Criteria crit = this.getSession().createCriteria(clazz);
		@SuppressWarnings("unchecked")
		List<T> liste = (List<T>) crit.list();
		return liste;
	}

	public <T> List<T> getListByQuery(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> void create(T entity) {
		this.getSession().save(entity);
	}

	public <T> void update(T entity) {
		this.getSession().update(entity);
	}

	public <T> void delete(T entity) {
		this.getSession().delete(entity);
	}
}