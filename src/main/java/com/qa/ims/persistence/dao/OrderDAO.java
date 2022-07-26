package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public List<Order> readAll() {
		ArrayList<Integer> orderIDs = new ArrayList<Integer>();
		List<Order> orders = new ArrayList<Order>();
		try {
			Connection con = DBUtils.getInstance().getConnection();;
			Statement stmt=con.createStatement();
			// select all tables joined on their foreign keys (Customer<Order<Orderitem>Item)
			ResultSet rs = stmt.executeQuery("select * from order_item "
					+ "join items "
					+ "on order_item.item_id = items.item_id "
					+ "join order_record "
					+ "on order_record.order_id = order_item.order_id "
					+ "join customers "
					+ "on customers.customer_id = order_record.customer_id");
			while(rs.next()) {
			// loop through each result, check if its a new order each time, creating a list of orderIDs and corresponding custIDs

				if (!orderIDs.contains(rs.getInt("order_item.order_id"))){
					orderIDs.add(rs.getInt("order_item.order_id"));
					orders.add(new Order(rs.getLong("order_item.order_ID"), rs.getLong("customers.customer_id"), rs.getString("customers.first_name")));
				}
				
				//rs.getInt("o.OrderID");
			}
			
			// for each order
			
			for (Order order:orders) {
				rs = stmt.executeQuery("select * from order_item "
						+ "join items "
						+ "on order_item.item_id = items.item_id "
						+ "join order_record "
						+ "on order_record.order_id = order_item.order_id "
						+ "join customers "
						+ "on customers.customer_id = order_record.customer_id "
						+ "where order_record.order_id = " + order.getOrderID());
				ArrayList<Item> itemsToAdd = new ArrayList<>();
				while (rs.next()) {
					itemsToAdd.add(new Item(rs.getLong("items.item_id"), rs.getString("items.item_name"), rs.getDouble("items.price")));
				
				}
				order.setItems(itemsToAdd);
			}
			return orders;
	}
	catch(Exception e) {
		LOGGER.info(e.getMessage());
		return null;
	}
		}
			
			// loop through the order creating an arraylist of items
				
			// return the order with the arraylist
			
			// create a new order item containing orderID, CustomerID, arraylist of items
			
			// add this order item to an arraylist of orders
			
			// return the arraylist of orders
			
			// for every order ID request all the joined tables corresponding to the order
			

	
// checks the last order that was created and returns it so that the id can be taken and used to attach an item
	public Order readLatest(Long id) {
		try (Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM order_record ORDER BY order_id DESC LIMIT 1");){
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return new Order(rs.getLong("order_id"), rs.getLong("customer_id"));
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}

	@Override
	public Order create(Order order) {
		try (Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("INSERT INTO order_record VALUES (0, ?)");){
			stmt.setLong(1, order.getCustomerID());
			stmt.execute();
			return order;
		}
		catch(Exception e) {
			LOGGER.info("Customer ID does not exist");
		}
		return null;
	}

	@Override
	public Order update(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		int result1;
		int result2;
		
		// deletes all records matching given order ID from the order item table
		try (Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("DELETE FROM order_item WHERE order_id = ?");){
					stmt.setLong(1, id);
					result1 = stmt.executeUpdate();
				}
				
		catch(Exception e) {
			LOGGER.info(e.getMessage());
			result1 = 0;
			
		}
		
		//deletes all records matching given order ID from the order record
		try (Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("DELETE FROM order_record WHERE order_id = ?");){
					stmt.setLong(1, id);
					result2 = stmt.executeUpdate();
				}
			
		catch(Exception e) {
			LOGGER.info(e.getMessage());
			result2 = 0;
		}
		
		if (result1 + result2 == 2) {
			return 1;
		}
		
		
		return 0;
		}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int productAdder(long orderid,long itemid) {
		try (Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("INSERT INTO order_item VALUES (0, ?, ?)")){
			stmt.setLong(1, orderid);
			stmt.setLong(2, itemid);
			return stmt.executeUpdate();
		}
		catch(Exception e) {
			LOGGER.warn(e.getMessage());
			return 0;
		}
	}
	
	public int productdeleter(long orderid, long itemid) {
		try (Connection con = DBUtils.getInstance().getConnection();
				//deletes from the order but only deletes one item as opposed to all matching items
				PreparedStatement stmt = con.prepareStatement("DELETE FROM order_item WHERE item_id = ? AND order_id = ? LIMIT 1");
				){
			stmt.setLong(1, itemid);
			stmt.setLong(2, orderid);
			return stmt.executeUpdate();
		}
		catch(Exception e) {
			LOGGER.info(e);
		}
		return 0;
	}
	
	
	
	@Override
	public Order read(Long id) {
		
		List<Order> orders = readAll();
		
		for (Order order: orders) {
			if (order.getOrderID()== id) {
				return order;
			}
		}
		
		return null;
	}
	
	public Double calculate(Long toCalculate) {
		Double total = 0D;
		List<Order> orders = readAll();
		for (Order order: orders) {
			if (order.getOrderID() == toCalculate){
				for (Item item: order.getItems()) {
					total += item.getPrice();
				}
			}
		}
		return total;
		
	}
	
}
