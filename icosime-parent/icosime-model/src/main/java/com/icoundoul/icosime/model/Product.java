package com.icoundoul.icosime.model;

import java.util.List;

public class Product extends DomainObject{

	private String name;
	private String description;
	private Category category;
	private List<Item> itemList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	/**
	 * Verify the mandatories attributes
	 */
	public void checkData(){
		
	}
	

}
