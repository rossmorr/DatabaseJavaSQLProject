package com.qa.ims.persistence.domain;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class itemTest {

	@Test
	public void testEquals() {
		
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
}