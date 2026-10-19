package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import contactPackage.Contact;
import contactPackage.ContactService;


class ContactServiceTest {
	
	ContactService service;
	
	@BeforeEach
	void createContactService() {
		service = new ContactService();
	}

	@Nested
	@DisplayName("Test Add Contact")
	class testAddContact {
		
		@Test
		@DisplayName("Test Adding Valid Contact")
		void testValidAddContact() {
			service.addContact("100", "Dave", "Guy", "1".repeat(10), "Dave's address");
			assertFalse(service.findContact("100") == null);
		}
		
		@Test
		@DisplayName("Test Adding A Duplicate Contact")
		void testDuplicateAddContact() {
			service.addContact("100", "Dave", "Guy", "1".repeat(10), "Dave's address");
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addContact("100", "Greg", "John", "2".repeat(10), "Greg's address");
			});
		}
		
		@Test
		@DisplayName("Test Adding Invalid Contacts")
		void testInvalidAddContact() {
			// ID Test
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addContact("1".repeat(11), "Dave", "Guy", "1".repeat(10), "Dave's address");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addContact(null, "Dave", "Guy", "1".repeat(10), "Dave's address");
			});
			
			// First Name Test
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", " ".repeat(11), "Guy", "1".repeat(10), "Dave's address");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", null, "Guy", "1".repeat(10), "Dave's address");
			});
			
			// Last Name Test
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", " ".repeat(11), "1".repeat(10), "Dave's address");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", null, "1".repeat(10), "Dave's address");
			});
			
			// Phone Number Test
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", "Guy", "1".repeat(11), "Dave's address");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", "Guy", "1".repeat(9), "Dave's address");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", "Guy", null, "Dave's address");
			});
			
			// Address Test
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", "Guy", "1".repeat(10), " ".repeat(31));
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("100", "Dave", "Guy", "1".repeat(10), null);
			});
		}
	}
	
	@Nested
	@DisplayName("Test Delete Contact")
	class testDeleteContact {
		
		@Test
		@DisplayName("Test Deleting Valid Contact")
		void testValidDeleteContact() {
			service.addContact("100", "Dave", "Guy", "1".repeat(10), "Dave's address");
			service.deleteContact("100");
			assertTrue(service.findContact("100") == null);
		}
		
		@Test
		@DisplayName("Test Deleting An Invalid Contact")
		void testInvalidDeleteContact() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.deleteContact("100");
			});
			
			service.addContact("100", "Dave", "Guy", "1".repeat(10), "Dave's address");
			service.deleteContact("100");
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.deleteContact("100");
			});
		}
	}
	
	@Nested
	@DisplayName("Test Update Contact")
	class testUpdateContact {
		
		@Test
		@DisplayName("Test Updating Each Field Individually")
		void testUpdateIndividualFields() {
			service.addContact("100", "oops", "wrong", "2".repeat(10), "my bad");
			Contact accidentContact = service.findContact("100");
			
			service.updateContact("100", "Dave", "", "", "");
			service.updateContact("100", "", "Guy", "", "");
			service.updateContact("100", "", "", "1".repeat(10), "");
			service.updateContact("100", "", "", "", "Dave's address");
			
			assertTrue(accidentContact.getID().equals("100"));
			assertTrue(accidentContact.getFirstName().equals("Dave"));
			assertTrue(accidentContact.getLastName().equals("Guy"));
			assertTrue(accidentContact.getPhone().equals("1".repeat(10)));
			assertTrue(accidentContact.getAddress().equals("Dave's address"));
		}
		
		@Test
		@DisplayName("Test Updating All Fields At Once")
		void testUpdateAllFields() {
			service.addContact("100", "oops", "wrong", "2".repeat(10), "my bad");
			Contact accidentContact = service.findContact("100");
			service.updateContact("100", "Dave", "Guy", "1".repeat(10), "Dave's address");
			
			assertTrue(accidentContact.getID().equals("100"));
			assertTrue(accidentContact.getFirstName().equals("Dave"));
			assertTrue(accidentContact.getLastName().equals("Guy"));
			assertTrue(accidentContact.getPhone().equals("1".repeat(10)));
			assertTrue(accidentContact.getAddress().equals("Dave's address"));
		}
		
		@Test
		@DisplayName("Test Updating No Fields")
		void testUpdateNoFields() {
			service.addContact("100", "Dave", "Guy", "1".repeat(10), "Dave's address");
			Contact correctContact = service.findContact("100");
			service.updateContact("100", "", "", "", "");
			
			assertTrue(correctContact.getID().equals("100"));
			assertTrue(correctContact.getFirstName().equals("Dave"));
			assertTrue(correctContact.getLastName().equals("Guy"));
			assertTrue(correctContact.getPhone().equals("1".repeat(10)));
			assertTrue(correctContact.getAddress().equals("Dave's address"));
		}
		
		@Test
		@DisplayName("Test Updating Invalid Contact")
		void testInvalidUpdateContact() {
			service.addContact("100", "Dave", "Guy", "1".repeat(10), "Dave's address");
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.updateContact("200", "", "", "", "");
			});
		}
	}

}
