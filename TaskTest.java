package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import taskPackage.Task;

class TaskTest {
	
	@Nested
	@DisplayName("Test Constructor")
	class testConstructor {
		
		@Test
		@DisplayName("Test Valid Constructor Use")
		void testValidConstructor() {
			Task newTask = new Task("1".repeat(10), "My Task", "Must Do");
			assertTrue(newTask.getID().equals("1".repeat(10)));
			assertTrue(newTask.getName().equals("My Task"));
			assertTrue(newTask.getDescription().equals("Must Do"));
		}
		
		@Test
		@DisplayName("Test Invalid Constructor ID")
		void testInvalidID() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("1".repeat(11), "My Task", "Must Do");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task(null, "My Task", "Must Do");
			});
		}
		
		@Test
		@DisplayName("Test Invalid Constructor Name")
		void testInvalidName() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("1".repeat(10), " ".repeat(21), "Must Do");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("1".repeat(10), null, "Must Do");
			});
		}
		
		@Test
		@DisplayName("Test Invalid Constructor Description")
		void testInvalidDescription() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("1".repeat(10), "My Task", " ".repeat(51));
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("1".repeat(10), "My Task", null);
			});
		}
	}
	
	@Nested
	@DisplayName("Test Setters")
	class testSetters {
		
		@Test
		@DisplayName("Test Valid SetName Inputs")
		void testSetName() {
			Task accidentTask = new Task("1".repeat(10), "Oops", "Must Do");
			accidentTask.setName("My Task");
			assertTrue(accidentTask.getName().equals("My Task"));
		}
		
		@Test
		@DisplayName("Test Valid SetDescription Inputs")
		void testSetDescription() {
			Task accidentTask = new Task("1".repeat(10), "My Task", "Oops");
			accidentTask.setDescription("Must Do");
			assertTrue(accidentTask.getDescription().equals("Must Do"));
		}
		
		@Test
		@DisplayName("Test Invalid SetName Inputs")
		void testInvalidSetName() {
			Task accidentTask = new Task("1".repeat(10), "Oops", "Must Do");
			
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				accidentTask.setName(" ".repeat(21));
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				accidentTask.setName(null);
			});
		}
		
		@Test
		@DisplayName("Test Invalid SetDescription Inputs") 
		void testInvalidSetDescription() {
			Task accidentTask = new Task("1".repeat(10), "My Task", "Oops");
			
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				accidentTask.setDescription(" ".repeat(51));
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				accidentTask.setDescription(null);
			});
		}
			
	}

}
