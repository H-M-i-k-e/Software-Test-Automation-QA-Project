package appointmentPackage;

import java.util.Date;

public class Appointment {
	
	private String appointmentID;
	private Date appointmentDate;
	private String description;
	
	public Appointment(String newAppointmentID, Date newAppointmentDate, String newDescription) {
		if (newAppointmentID == null || newAppointmentID.length() > 10) {
			throw new IllegalArgumentException("Invalid Appointment ID");
		}
		
		this.appointmentID = newAppointmentID;
		setAppointmentDate(newAppointmentDate);
		setAppointmentDescription(newDescription);
	}
	
	public void setAppointmentDate(Date newAppointmentDate) {
		if (newAppointmentDate == null || newAppointmentDate.before(new Date())) {
			throw new IllegalArgumentException("Invalid Appointment ID");
		}
		this.appointmentDate = newAppointmentDate;
	}
	
	public void setAppointmentDescription(String newDescription) {
		if (newDescription == null || newDescription.length() > 50) {
			throw new IllegalArgumentException("Invalid Appointment Description");
		}
		this.description = newDescription;
	}
	
	public String getID(){  return appointmentID;  }
	public Date getDate(){ return appointmentDate;  }
	public String getDescription(){  return description;  }
	
}