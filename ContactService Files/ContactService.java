package contactPackage;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
	
	private Map<String, Contact> contactList =  new HashMap<>();
	
	public ContactService() {}
	
	public Contact findContact(String targetID) {
		if (contactList.get(targetID) != null) {
			return contactList.get(targetID);
		}
		return null;
	}
	
	public void addContact(String contactID, String contactFirstName, String contactLastName, String contactPhone, String contactAddress) {
		if (findContact(contactID) != null) {
			throw new IllegalArgumentException("Contact ID Already Exists");
		}
		contactList.put(contactID, new Contact(contactID, contactFirstName, contactLastName, contactPhone, contactAddress));
	}
	
	public void deleteContact(String targetID) {
		if (findContact(targetID) == null) {
			throw new IllegalArgumentException("Contact ID Does Not Exist");
		}
		contactList.remove(targetID);
	}
	
	public void updateContact(String targetID, String newFirstName, String newLastName, String newPhone, String newAddress) {
		Contact targetContact = findContact(targetID);
		
		if (targetContact == null) {
			throw new IllegalArgumentException("Contact ID Does Not Exist");
		}
		
		if (newFirstName != "") {
			targetContact.setFirstName(newFirstName);
		}
		if (newLastName != "") {
			targetContact.setLastName(newLastName);
		}
		if (newPhone != "") {
			targetContact.setPhone(newPhone);
		}
		if (newAddress != "") {
			targetContact.setAddress(newAddress);
		}
	}
	
}

