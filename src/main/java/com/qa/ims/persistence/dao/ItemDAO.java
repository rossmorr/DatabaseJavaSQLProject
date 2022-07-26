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

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Item> {
	public static final Logger LOGGER = LogManager.getLogger();
	@Override
	public List<Item> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item create(Item t) {
		try(Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("INSERT INTO Items VALUES (0, ?, ?)");
				){
			stmt.setString(1, t.getName());
			stmt.setDouble(2, t.getPrice());
			stmt.execute();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Item update(Item t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		try(Connection con = DBUtils.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("DELETE FROM items WHERE item_id = ?");){
				stmt.setLong(1, id);
				stmt.executeUpdate();
				
		}
		catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
