package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		return null;
	}
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
		}
		catch(Exception e) {
			LOGGER.info("Customer ID does not exist");
		}
		return null;
	}

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void productAdder(long orderid,long itemid) {
		try (Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("INSERT INTO order_item VALUES (0, ?, ?)")){
			stmt.setLong(1, orderid);
			stmt.setLong(2, itemid);
			stmt.executeUpdate();
		}
		catch(Exception e) {
			LOGGER.warn(e.getMessage());
		}
	}
	@Override
	public Order read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
