package contactPackage;

public class Contact {
	
	private String id;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	
	public Contact(String newID, String newFirstName, String newLastName, String newPhone, String newAddress) {
		if (newID == null || newID.length() > 10) {
			throw new IllegalArgumentException("Invalid Contact ID");
		}
		
		this.id = newID;
		setFirstName(newFirstName);
		setLastName(newLastName);
		setPhone(newPhone);
		setAddress(newAddress);
	}
	
	public void setFirstName(String newFirstName) { 
		if (newFirstName == null || newFirstName.length() > 10) {
			throw new IllegalArgumentException("Invalid Contact First Name");
		}
		this.firstName = newFirstName; 
	}
	
	public void setLastName(String newLastName) { 
		if (newLastName == null || newLastName.length() > 10) {
			throw new IllegalArgumentException("Invalid Contact Last Name");
		}	
		this.lastName = newLastName; 
	}
	
	public void setPhone(String newPhone) { 
		if (newPhone == null || newPhone.length() != 10) {
			throw new IllegalArgumentException("Invalid Contact Phone Number");
		}	
		this.phone = newPhone; 
	}
	
	public void setAddress(String newAddress) { 
		if (newAddress == null || newAddress.length() > 30) {
			throw new IllegalArgumentException("Invalid Contact Address");
		}	
		this.address = newAddress; 
	}
	
	public String getID() { return id; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getPhone() { return phone; }
	public String getAddress() { return address; }
	
}
