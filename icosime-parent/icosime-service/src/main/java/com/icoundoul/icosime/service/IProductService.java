package com.icoundoul.icosime.service;

import java.util.List;

import com.icoundoul.icosime.dao.exception.DAOException;
import com.icoundoul.icosime.model.Product;
import com.icoundoul.icosime.service.exception.IcoSimeException;

public interface IProductService {
	public void createProduct(Product product) throws IcoSimeException;
	public void updateProduct(Product product) throws IcoSimeException;
	public void removeProduct(Product product)throws IcoSimeException;
	public Product getProduct(int id) throws IcoSimeException;
	public List<Product> getAllProducts()throws IcoSimeException;

}
