package com.icoundoul.icosime.model;

public class Item extends DomainObject{

	private String name;
	private double unitCost;
	private Product product;
	private String image;
	
	public Item(){}
	
	public Item(int id, String name, double unitCost, int product_fk, String image) {
		this.id = id;
		this.name = name;
		this.unitCost = unitCost;
		product = new Product(); 
		product.setId(product_fk);
		this.image = image;
	}
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
	public Double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * Verify the mandatories attributes
	 */
	public void checkData(){
		
	}
	
}
