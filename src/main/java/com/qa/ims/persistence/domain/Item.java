package com.qa.ims.persistence.domain;

public class Item {
	
	private Long id;
	private String name;
	private Double price;
	
	public Item(long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	
	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " Price: " + price;
	}
}
