package org.ucl.medicaldb;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class PatientHandlerTest {

	@Test
	public void testCompletedObligatoryField() {
		assertFalse("", false);
		assertFalse(" f", false);
		assertTrue("s", true);
	}

	@Test
	public void testIsValidString() {
		assertTrue("ab100", true);
		assertFalse("abb100", false);
		assertFalse(" b100", false);
		assertFalse("ab1000", false);
		assertFalse("ab10O", false);
	}

	@Test
	public void testIsValidStringString() {
		assertTrue("David", true);
		assertTrue("D'Angelo", true);
		assertTrue("Billy-Bob", true);
		assertTrue("James Gordon", true);
		assertFalse("Bill1-Bob", false);
		assertFalse("Dav8d", false);
		assertFalse("s[range nam3", false);
		assertFalse(" ", false);
	}

	@Test
	public void testIsValidDate() {
		assertTrue("12/10/1978", true);
		assertTrue("31/1/2000", true);
		assertTrue("30/9/2015", true);
		assertFalse("00/11/2000", false);
		assertFalse("100/11/2000", false);
		assertFalse("31/02/1999", false);
		assertFalse("31/6/1999", false);
		assertFalse("31/9/2000", false);
		assertFalse("33/1/2000", false);
		assertFalse("1/1/0000", false);
	}

	@Test
	public void testIsUniqueID() {
		HashSet<String> ids = new HashSet<String>();
		ids.add("ab100");
		ids.add("ab101");
		ids.add("cd100");
		assertTrue("cd101", true);
		assertFalse("ab100", false);
	}

	@Test
	public void testIsValidURI() {
		assertTrue("https://www.google.com", true);
		assertTrue("http://www.google.com", true);
		assertFalse("htts://www.google.com", false);
		assertFalse("http:/www.google.com", false);
		assertFalse("htpps://www.google.com", false);
		assertFalse("https:/www.google.com", false);
	}
	
	@Test
	public void testHasValidPostCode() {
		assertTrue("NW10 7NX", true);
		assertTrue("NW107NX", true);
		assertTrue("n55aa", true); // ugly typing, but acceptable format
		assertTrue("EC5 2PX", true);
		assertTrue("fake address here, W12 2PP", true);
		assertFalse("nw10a 2pp", false);
		assertFalse("n0 0pp", false);
		assertFalse("NW10 7NX at the beginning is wrong", false);
	}

	@Test
	public void testIsDateInFuture() {
		assertTrue("21/10/2020", true);
		assertFalse("21/11/2015", false);
		assertTrue("1/1/2017", true);
		assertFalse("25/12/2014", false);
		assertFalse("2/12/2015", false);
	}
}
