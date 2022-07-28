package com.qa.ims.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;
	
	@Mock
	private ItemDAO dao1;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final String response = "order" ;
		final Long custID = 1L;
		final Long itemID = 1L;
		final long orderID = 1;
		final Order passin = new Order(custID);
		final Order finalreturn = new Order(orderID,custID);
		final Item toreturn = new Item(1, "Chocolate", 1.50);
		

		Mockito.when(utils.getString()).thenReturn(response);
		Mockito.when(utils.getLong()).thenReturn(custID, itemID);
		Mockito.when(dao1.read(itemID)).thenReturn(toreturn);
		Mockito.when(dao.create(passin)).thenReturn(passin);
		Mockito.when(dao.readLatest(custID)).thenReturn(finalreturn);

		assertEquals(passin, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(passin);
		Mockito.verify(dao, Mockito.times(1)).readLatest(custID);
		Mockito.verify(dao1, Mockito.times(1)).read(itemID);
		
	}
	
	@Test
	public void testCreateItem() {
		
		final String response = "item";
		final Long orderID = 1L;
		final Long itemID = 1L;
		
		Mockito.when(utils.getString()).thenReturn(response);
		Mockito.when(utils.getLong()).thenReturn(orderID, itemID);
		
		assertEquals(null, controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(2)).getLong();
		
		
	}
	

	@Test
	public void testReadAll() {
		final String response = "orders";
		List<Order> orders = new ArrayList<>();
		Mockito.when(utils.getString()).thenReturn(response);
		Mockito.when(dao.readAll()).thenReturn(orders);
		
		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
		Mockito.verify(utils, Mockito.times(1)).getString();
	}
	
	

	@Test
	public void testDelete() {
		
		final String response = "order";
		final long response2 = 1L;

		Mockito.when(utils.getString()).thenReturn(response);
		Mockito.when(utils.getLong()).thenReturn(response2);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getLong();
	}
	
	@Test
	public void testDeleteItem() {
		final String response = "item";
		final long response2 = 1L;
		final long response3 = 1L;

		Mockito.when(utils.getString()).thenReturn(response);
		Mockito.when(utils.getLong()).thenReturn(response2, response3);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(2)).getLong();
	}
	
	@Test
	public void testReadCost() {
		final String response = "cost";
		final long response2 = 1L;
		List<Order> orders = new ArrayList<>();
		
		Mockito.when(utils.getString()).thenReturn(response);
		Mockito.when(utils.getLong()).thenReturn(response2);
		
		assertEquals(orders, this.controller.readAll());
		
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		
	}

}
