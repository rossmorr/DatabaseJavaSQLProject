package com.qa.ims.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>{
	
	public static final Logger LOGGER = LogManager.getLogger();
	private Utils utils;
	private OrderDAO orderDAO;
	private ItemDAO itemDAO;
	
	public OrderController(OrderDAO orderDAO, Utils utils, ItemDAO itemDAO) {
		this.utils = utils;
		this.orderDAO = orderDAO;
		this.itemDAO = itemDAO;
	}

	@Override
	public List<Order> readAll() {
		LOGGER.info("Would you like to view the 'ORDERS', or calculate the 'COST' of an order?");
		String resp =utils.getString().toLowerCase();
		List<Order> orders = orderDAO.readAll();
		//setting the cost format to 2 decimal places
		Locale locale = Locale.ENGLISH;
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		nf.setMinimumFractionDigits(2);
		
		switch(resp) {
		case("orders"):
		
		

			for (Order order: orders) {
				LOGGER.info("---------Order# [" + order.getOrderID() + "]" + " Customer# [" + order.getCustomerID() + "] [" + order.getCustomerName() +"]---------");
				for (Item item: order.getItems()) {
					LOGGER.info(item.getName() + "    £" + nf.format(item.getPrice()));
				}
			}
			return orders;
		
		case("cost"):
			LOGGER.info("Which order would you like the total cost of?");
			Long orderToCost = utils.getLong();
			// goes through each order item checking if the orderID matches user input, then if it does it outputs products and total price
			for (Order order: orders) {
				if (order.getOrderID() == orderToCost) {
					LOGGER.info("---------Order# [" + order.getOrderID() + "]" + " Customer# [" + order.getCustomerID() + "] [" + order.getCustomerName() +"]---------");
					for (Item item: order.getItems()) {
						LOGGER.info(item.getName() + "    £" + nf.format(item.getPrice()));
					}
					LOGGER.info("Total Price:      £" + nf.format(orderDAO.calculate(orderToCost)));
				}
			}
			return orders;
			
		
	}
		return orders;
	}
		
		

	@Override
	public Order create() {
		
		
		LOGGER.info("Would you like to add an 'ITEM' to an order or create a new 'ORDER'?");
		String response = utils.getString();
		response = response.toLowerCase();
		switch(response) {
		  case "order":
				
				
				LOGGER.info("Which customer would you like to create an order for?");
				Long custID = utils.getLong();
				LOGGER.info("Please choose an initial item to add to the order");
				Long itemID = utils.getLong();
				
				// checks that the item exists
				if (itemDAO.read(itemID)!= null) {
					Order passin = new Order(custID);
					//creates a new order using the customer ID as a foreign key
					orderDAO.create(passin);
					//gets the ID of the order it just created
					long orderID = orderDAO.readLatest(custID).getOrderID();
					//adds an item to that order
					orderDAO.productAdder(orderID, itemID);
					return passin;
				}
				else {
					LOGGER.info("The item ID does not exist");
				}
				break;
				
		  case "item":
		    
				LOGGER.info("Which order would you like to add the item to?");
				Long order = utils.getLong();
				LOGGER.info("Which item would you like to add?");
				Long prod = utils.getLong();
				orderDAO.productAdder(order, prod);
				
			  
		    break;
		  default:
		    LOGGER.info("Invalid response");
		}
		return null;
		

	}

	@Override
	public Order update() {
		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("Would you like to delete an ITEM or an ORDER?");
		String response = utils.getString();
		response = response.toLowerCase();
		switch(response) {
		  case "item":
				
				LOGGER.info("Which order would you like to delete from?");
				long orderid = utils.getLong();
				LOGGER.info("Which item would you like to delete?");
				long itemid = utils.getLong();
				orderDAO.productdeleter(orderid, itemid);
				return 1;
				
		case "order":
		    
			  LOGGER.info("Which order would you like to delete?");
			  long orderdelete = utils.getLong();			  
		      orderDAO.delete(orderdelete);
			  return 1;
			  
		default:
		    LOGGER.info("Invalid response");
		    return 0;
		}
	}
	
}