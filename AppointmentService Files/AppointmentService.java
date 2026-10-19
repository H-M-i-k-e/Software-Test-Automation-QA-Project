package appointmentPackage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
	
	private Map<String, Appointment> appointmentList = new HashMap<>();
	
	public AppointmentService() {}
	
	public Appointment findAppointment(String targetID) {
		if (appointmentList.get(targetID) != null) {
			return appointmentList.get(targetID);
		}
		return null;
	}
	
	public void addAppointment(String appointmentID, Date appointmentDate, String description) {
		if (findAppointment(appointmentID) != null) {
			throw new IllegalArgumentException("Appointment ID Already Exists");
		}
		appointmentList.put(appointmentID, new Appointment(appointmentID, appointmentDate, description));
	}
	
	public void deleteAppointment(String targetID) {
		if (findAppointment(targetID) == null) {
			throw new IllegalArgumentException("Appointment ID Does Not Exist");
		}
		appointmentList.remove(targetID);
	}

}
