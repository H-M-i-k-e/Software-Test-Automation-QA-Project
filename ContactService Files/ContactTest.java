package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import contactPackage.Contact;

class ContactTest {
	
	@Nested
	@DisplayName("Test Constructor")
	class testConstructor {
		
		@Test
		@DisplayName("Test Valid Constructor Use")
		void testValidConstructor() {
			Contact correctContact = new Contact("100", "Dave", "Guy", "1".repeat(10), "Dave's address");
			assertTrue(correctContact.getID().equals("100"));
			assertTrue(correctContact.getFirstName().equals("Dave"));
			assertTrue(correctContact.getLastName().equals("Guy"));
			assertTrue(correctContact.getPhone().equals("1".repeat(10)));
			assertTrue(correctContact.getAddress().equals("Dave's address"));
		}
		
		@Test
		@DisplayName("Test Invalid Constructor ID")
		void testInvalidID() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("1".repeat(11), "Dave", "Guy", "1".repeat(10), "Dave's address");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact(null, "Dave", "Guy", "1".repeat(10), "Dave's address");
			});
		}
		
		@Test
		@DisplayName("Test Invalid Constructor First Name")
		void testInvalidFirstName() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", " ".repeat(11), "Guy", "1".repeat(10), "Dave's address");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", null, "Guy", "1".repeat(10), "Dave's address");
			});
		}
		
		@Test
		@DisplayName("Test Invalid Constructor Last Name")
		void testInvalidLastName() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", " ".repeat(11), "1".repeat(10), "Dave's address");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", null, "1".repeat(10), "Dave's address");
			});
		}
		
		@Test
		@DisplayName("Test Invalid Constructor Phone Number")
		void testInvalidPhone() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", "Guy", "1".repeat(11), "Dave's address");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", "Guy", "1".repeat(9), "Dave's address");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", "Guy", null, "Dave's address");
			});
		}
		
		@Test
		@DisplayName("Test Invalid Constructor Address")
		void testInvalidAddress() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", "Guy", "1".repeat(10), " ".repeat(31));
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", "Guy", "1".repeat(10), null);
			});
		}
		
	}
	
	@Nested
	@DisplayName("Test Setters")
	class testSetters {
		
		@Test
		@DisplayName("Test Valid Set First Name Input")
		void testValidSetFirstName() {
			Contact accidentContact = new Contact("100", "Oops", "Guy", "1".repeat(10), "Dave's address");
			accidentContact.setFirstName("Dave");
			assertTrue(accidentContact.getFirstName().equals("Dave"));
		}
		
		@Test
		@DisplayName("Test Valid Set Last Name Input")
		void testValidSetLastName() {
			Contact accidentContact = new Contact("100", "Dave", "Oops", "1".repeat(10), "Dave's address");
			accidentContact.setLastName("Guy");
			assertTrue(accidentContact.getLastName().equals("Guy"));
		}
		
		@Test
		@DisplayName("Test Valid Set Phone Input")
		void testValidSetPhone() {
			Contact accidentContact = new Contact("100", "Dave", "Guy", "2".repeat(10), "Dave's address");
			accidentContact.setPhone("1".repeat(10));
			assertTrue(accidentContact.getPhone().equals("1".repeat(10)));
		}
		
		@Test
		@DisplayName("Test Valid Set Address Input")
		void testValidSetAddress() {
			Contact accidentContact = new Contact("100", "Dave", "Guy", "1".repeat(10), "Oops");
			accidentContact.setAddress("Dave's address");
			assertTrue(accidentContact.getAddress().equals("Dave's address"));
		}
	}

}
