package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Item> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	
	public List<Item> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Item read(Long id) {
		try (Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM items WHERE item_id = ?");){
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getDouble("price"));
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	@Override
	public Item create(Item t) {
		try(Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("INSERT INTO Items(item_name, price) VALUES (?, ?)");
				){
			stmt.setString(1, t.getName());
			stmt.setDouble(2, t.getPrice());
			stmt.execute();
			return t;
		}
		catch(Exception e) {
			System.out.println(e + "ITS THIS ONE");
		}
		return null;
	}

	@Override
	public Item update(Item t) {
		try(Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("UPDATE items SET item_name = ?, Price = ? WHERE item_id = ?");) {
			stmt.setString(1, t.getName());
			stmt.setDouble(2, t.getPrice());
			stmt.setLong(3, t.getId());
			stmt.executeUpdate();
			return read(t.getId());
		}
		catch(Exception e) {
		}
		return null;
	}

	@Override
	public int delete(long id) {
		try(Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("DELETE FROM items WHERE item_id = ?");){
				stmt.setLong(1, id);
				return stmt.executeUpdate();
				
		}
		catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("item_id");
		String name = resultSet.getString("item_name");
		Double price = resultSet.getDouble("price");
		return new Item(id, name, price);
	}
	
	
}
