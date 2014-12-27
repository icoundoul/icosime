package com.icoundoul.icosime.service;

import java.util.List;

import com.icoundoul.icosime.model.Category;
import com.icoundoul.icosime.service.exception.IcoSimeException;

public interface ICategoryService {
	
	public void createCategory(Category category) throws IcoSimeException;
	public void updateCategory(Category category) throws IcoSimeException;
	public void removeCategory(Category category)throws IcoSimeException;
	public Category getCategory(int id) throws IcoSimeException;
	public List<Category> getAllCategorys()throws IcoSimeException;

}
