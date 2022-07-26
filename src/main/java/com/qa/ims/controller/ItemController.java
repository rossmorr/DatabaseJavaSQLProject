package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;


public class ItemController implements CrudController<Item> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private ItemDAO itemDAO;
	private Utils utils;
	
	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	@Override
	public List<Item> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item create() {
		LOGGER.info("Please enter the product name");
		String name = utils.getString();
		LOGGER.info("Please enter the product price");
		double price = utils.getDouble();
		Item item = itemDAO.create(new Item(name, price));
		LOGGER.info("Product created");
		return item;
	}
	@Override
	public Item update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the product you would like to delete");
		long id = utils.getLong();
		itemDAO.delete(id);
		return 0;
	}
	
}