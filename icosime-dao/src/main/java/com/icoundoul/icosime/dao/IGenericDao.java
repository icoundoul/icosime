package com.icoundoul.icosime.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao {

		<T> T get(Class<T> clazz, Serializable id);
	
		<T> List<T> getList(Class<T> clazz);

		<T> List<T> getListByQuery(String queryString);
			    
	    <T> void create(final T entity);
	    
	    <T> void update(final T entity);
		   
	   // <T> void deleteById(Class<T> clazz, Serializable id);
	    
	    <T> void delete(final T entity);		
}
