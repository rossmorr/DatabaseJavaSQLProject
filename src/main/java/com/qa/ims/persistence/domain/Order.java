package com.qa.ims.persistence.domain;

import java.util.ArrayList;

public class Order {
	private Long orderID;
	private Long customerID;
	private String customerName;
	private ArrayList<Item> items;
	
	public Order(Long custID) {
		this.customerID = custID;
	}
	public Order(long orderID, long customerID) {
		this.orderID = orderID;
		this.customerID = customerID;
	}
	public Order(long orderID, long customerID, String name) {
		this.setCustomerName(name);
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
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "id: " + orderID + "customerID" + customerID + " customerName: " + customerName + " Items: " + items;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (getCustomerName() == null) {
			if (other.getCustomerName() != null)
				return false;
		} else if (!getCustomerName().equals(other.getCustomerName()))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		if (getItems() == null) {
			if (other.getItems() != null)
				return false;
		} else if (!getItems().equals(other.getItems()))
			return false;
		return true;
	}
	
}
