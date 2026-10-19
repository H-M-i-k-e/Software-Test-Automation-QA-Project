package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import appointmentPackage.AppointmentService;
import appointmentPackage.Appointment;

import java.util.Calendar;
import java.util.Date;

class AppointmentServiceTest {
	
	static private Date oneDayInFuture;
	static private Date pastDate;
	
	AppointmentService service;
	
	@BeforeAll
	@DisplayName("Set Up Test Dates")
	static void createTestDates() {
		Calendar calendar = Calendar.getInstance();
		Date currentDate = new Date();
		
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		oneDayInFuture = calendar.getTime();
		
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		pastDate = calendar.getTime();
	}
	
	@BeforeEach
	void createAppointmentService() {  service = new AppointmentService();  }
	
	@Nested
	@DisplayName("Add Appointment Tests")
	class testAddAppointment {
		
		@Test
		@DisplayName("Test Adding Valid Appointment")
		void testValidAddAppointment() {
			service.addAppointment("1".repeat(10), oneDayInFuture, "Cool Description");
			Appointment newAppointment = service.findAppointment("1".repeat(10));
			assertTrue(newAppointment != null);
			assertTrue(newAppointment.getID().equals("1".repeat(10)));
			assertTrue(newAppointment.getDate().equals(oneDayInFuture));
			assertTrue(newAppointment.getDescription().equals("Cool Description"));
		}
		
		@Test
		@DisplayName("Test Adding Duplicate Appointment IDs")
		void testDuplicateAddAppointment() {
			service.addAppointment("1".repeat(10), oneDayInFuture, "Cool Description");
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addAppointment("1".repeat(10), oneDayInFuture, "Really Cool Description");
			});
		}
		
		@Test
		@DisplayName("Test Adding Invalid Appointments")
		void testInvalidAddAppointment() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addAppointment("1".repeat(11), oneDayInFuture, "Cool Description");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addAppointment(null, oneDayInFuture, "Cool Description");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addAppointment("1".repeat(10), pastDate, "Cool Description");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addAppointment("1".repeat(10), null, "Cool Description");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addAppointment("1".repeat(10), oneDayInFuture, " ".repeat(51));
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addAppointment("1".repeat(10), oneDayInFuture, null);
			});
		}
		
	}
	
	@Nested
	@DisplayName("Delete Appointment Tests")
	class testDeleteAppointment {
		
		@Test
		@DisplayName("Test Deleting Valid Appointment")
		void testValidDeleteAppointment() {
			service.addAppointment("1".repeat(10), oneDayInFuture, "Cool Description");
			assertTrue(service.findAppointment("1".repeat(10)) != null);
			service.deleteAppointment("1".repeat(10));
			assertTrue(service.findAppointment("1".repeat(10)) == null);
		}
		
		@Test
		@DisplayName("Test Deleting Invalid Appointments")
		void testInvalidDeleteAppointment() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.deleteAppointment("1".repeat(10));
			});
			
			service.addAppointment("1".repeat(10), oneDayInFuture, "Cool Description");
			service.deleteAppointment("1".repeat(10));
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.deleteAppointment("1".repeat(10));
			});
		}
		
	}
}
