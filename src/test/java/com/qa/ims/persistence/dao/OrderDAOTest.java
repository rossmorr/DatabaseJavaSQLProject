package com.qa.ims.persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	
	private final OrderDAO DAO = new OrderDAO();

	@BeforeEach
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(1,1);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		ArrayList<Item> expectedItems = new ArrayList<>();
		expectedItems.add(new Item(1 , "Chocolate" , 1.5D));
		Order order = new Order(1L, 1L, "jordan");
		order.setItems(expectedItems);
		expected.add(order);
		assertEquals(expected,DAO.readAll());
	}

	@Test
	public void testRead() {
		ArrayList<Item> expectedItems = new ArrayList<>();
		expectedItems.add(new Item(1 , "Chocolate" , 1.5D));
		Order order = new Order(1L, 1L, "jordan");
		order.setItems(expectedItems);
		assertEquals(order, DAO.read(1L));
	}
	
	@Test
	public void testReadLatest() {
		Long toRead = 1L;
		Order order = new Order(1L,1L);
		assertEquals(order, DAO.readLatest(toRead));
		
		
	}


	@Test
	public void testUpdate() {
		Order order = new Order(1L,1L);
		assertEquals(null, DAO.update(order));

	}
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1L));
	}
	
	@Test
	public void testproductAdder() {
		int totest = DAO.productAdder(1L,1L);
		assertEquals(1, totest);
	}
	
	@Test
	public void testproductDeleter() {
		assertEquals(1, DAO.productdeleter(1L, 1L));
	}
	
	@Test
	public void testcalculate() {
		assertEquals(Double.valueOf(1.50), DAO.calculate(1L));
	}
	
}