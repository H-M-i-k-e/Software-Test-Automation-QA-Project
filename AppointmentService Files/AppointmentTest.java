package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import appointmentPackage.Appointment;

import java.util.Calendar;
import java.util.Date;

class AppointmentTest {
	
	static private Date oneDayInFuture;
	static private Date twoDaysInFuture;
	static private Date pastDate;
	
	@BeforeAll
	@DisplayName("Set Up Test Dates")
	static void createTestDates() {
		Calendar calendar = Calendar.getInstance();
		Date currentDate = new Date();
		
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		oneDayInFuture = calendar.getTime();
		
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		twoDaysInFuture = calendar.getTime();
		
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		pastDate = calendar.getTime();
	}
	
	@Nested
	@DisplayName("Constructor Tests")
	class testConstructor {
		
		@Test
		@DisplayName("Test Valid Constructor Use")
		void testValidConstructor() {
			Appointment newAppointment = new Appointment("1".repeat(10), oneDayInFuture, " ".repeat(50));
			assertTrue(newAppointment.getID().equals("1".repeat(10)));
			assertTrue(newAppointment.getDate().equals(oneDayInFuture));
			assertTrue(newAppointment.getDescription().equals(" ".repeat(50)));
		}
		
		@Test
		@DisplayName("Test Invalid Constructor ID")
		void testInvalidConstructorID() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("1".repeat(11), oneDayInFuture, "Cool Description");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment(null, oneDayInFuture, "Cool Description");
			});
		}
		
		@Test
		@DisplayName("Test Invalid Constructor Date")
		void testInvalidConstructorDate() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("1".repeat(10), pastDate, "Cool Description");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("1".repeat(10), null, "Cool Description");
			});
		}
		
		@Test
		@DisplayName("Test Invalid Constructor Description")
		void testInvalidConstructorDescription() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("1".repeat(10), oneDayInFuture, " ".repeat(51));
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("1".repeat(10), oneDayInFuture, null);
			});
		}
		
	}
	
	@Nested
	@DisplayName("Test Setters")
	class testSetters {
		
		@Test
		@DisplayName("Test Setting A Valid Appointment Date")
		void testSettingValidDate() {
			Appointment accidentAppointment = new Appointment("1".repeat(10), oneDayInFuture, "Cool Description");
			assertTrue(accidentAppointment.getDate().equals(oneDayInFuture));
			accidentAppointment.setAppointmentDate(twoDaysInFuture);
			assertTrue(accidentAppointment.getDate().equals(twoDaysInFuture));
		}
		
		@Test
		@DisplayName("Test Setting A Valid Appointment Description")
		void testSettingValidDescription() {
			Appointment accidentAppointment = new Appointment("1".repeat(10), oneDayInFuture, "Oops");
			assertTrue(accidentAppointment.getDescription().equals("Oops"));
			accidentAppointment.setAppointmentDescription("Cool Description");
			assertTrue(accidentAppointment.getDescription().equals("Cool Description"));
		}
		
		@Test
		@DisplayName("Test Setting An Invalid Appointment Date")
		void testSettingInvalidDate() {
			Appointment accidentAppointment = new Appointment("1".repeat(10), oneDayInFuture, "Cool Description");
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				accidentAppointment.setAppointmentDate(pastDate);
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				accidentAppointment.setAppointmentDate(null);
			});
		}
		
		@Test
		@DisplayName("Test Setting An Invalid Appointment Description")
		void testSettingInvalidDescription() {
			Appointment accidentAppointment = new Appointment("1".repeat(10), oneDayInFuture, "Oops");
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				accidentAppointment.setAppointmentDescription(" ".repeat(51));
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				accidentAppointment.setAppointmentDescription(null);
			});
		}
		
	}

}
