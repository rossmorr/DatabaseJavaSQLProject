package com.qa.ims.persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	
	private final ItemDAO DAO = new ItemDAO();

	@BeforeEach
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Item created = new Item(2L, "Pringles", 3.50);
//		System.out.println(DAO.create(created));
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "Chocolate", 1.50));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Item (1L, "Chocolate", 1.5d), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Chocolate", 1.50);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		//an item is added so that it can be deleted to ensure no foreign key dependancies
		final Item created = new Item(2L, "Pringles", 3.50);
		DAO.create(created);
		assertEquals(1, DAO.delete(2));
	}
}
