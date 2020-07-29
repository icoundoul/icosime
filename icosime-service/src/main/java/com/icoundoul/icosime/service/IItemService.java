package com.icoundoul.icosime.service;

import java.util.List;

import com.icoundoul.icosime.dao.exception.DAOException;
import com.icoundoul.icosime.model.Item;
import com.icoundoul.icosime.service.exception.IcoSimeException;

public interface IItemService {
	
	public void createItem(Item item) throws IcoSimeException;
	public void updateItem(Item item) throws IcoSimeException;
	public void removeItem(Item item)throws IcoSimeException;
	public List<Item> getAllItems()throws IcoSimeException;
}
