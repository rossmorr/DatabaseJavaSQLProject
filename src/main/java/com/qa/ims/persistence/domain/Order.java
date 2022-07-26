package com.qa.ims.persistence.domain;

import java.util.ArrayList;

public class Order {
	private long orderID;
	private long customerID;
	private ArrayList<Item> items;
	
	public Order(Long custID) {
		this.customerID = custID;
	}
	public Order(long orderID, long customerID) {
		this.orderID = orderID;
		this.customerID = customerID;
	}
	public long getOrderID() {
		return orderID;
	}
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	public ArrayList<Item> getProducts() {
		return items;
	}
	public void setProducts(ArrayList<Item> items) {
		this.items = items;
	}

}
