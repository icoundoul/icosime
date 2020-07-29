package com.icoundoul.icosime.model;

import java.util.List;

public class Category extends DomainObject{

	private String name;
	private String description;
	private List<Product> productList;
	
	public Category(){
		
	}
	
	public Category(int id, String name, String description) {
		this.id = id;
		this.name =name;
		this.description = description;		
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
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	/**
	 * Verify the mandatories attributes
	 */
	public void checkData(){
		
	}
	
}
