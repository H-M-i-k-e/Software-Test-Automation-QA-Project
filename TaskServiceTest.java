package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import taskPackage.TaskService;

class TaskServiceTest {
	
	TaskService service;
	
	@BeforeEach
	void createTaskService() {
		service = new TaskService();
	}

	@Nested
	@DisplayName("Test Add Task")
	class testAddTask {
		
		@Test
		@DisplayName("Test Adding Valid Task")
		void testValidAddTask() {
			service.addTask("1".repeat(10), "My Task", "Must Do");
			assertFalse(service.findTask("1".repeat(10)) == null);
		}
		
		
		@Test
		@DisplayName("Test Adding A Duplicate Task")
		void testDuplicateAddTask() {
			service.addTask("1".repeat(10), "My Task", "Must Do");
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addTask("1".repeat(10), "My Other Task", "Must Do Soon");
			});
		}
		
		@Test
		@DisplayName("Test Adding Invalid Tasks")
		void testInvalidAddTask() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addTask("1".repeat(11), "My Task", "Must Do Soon");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addTask(null, "My Task", "Must Do Soon");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addTask("1".repeat(10), " ".repeat(21), "Must Do Soon");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addTask("1".repeat(10), null, "Must Do Soon");
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addTask("1".repeat(10), "My Task", " ".repeat(51));
			});
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.addTask("1".repeat(10), "My Task", null);
			});
		}
	}
	
	@Nested
	@DisplayName("Test Delete Task")
	class tastDeleteTask {
		
		@Test
		@DisplayName("Test Deleting A Valid Task")
		void testValidDeleteTask() {
			service.addTask("1".repeat(10), "My Task", "Must Do");
			service.deleteTask("1".repeat(10));
			assertTrue(service.findTask("1".repeat(10)) == null);
		}
		
		@Test
		@DisplayName("Test Deleting An Invalid Task")
		void testInvalidDeleteTask() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.deleteTask("1".repeat(10));
			});
			
			service.addTask("1".repeat(10), "My Task", "Must Do");
			service.deleteTask("1".repeat(10));
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.deleteTask("1".repeat(10));
			});
		}
	}
	
	@Nested
	@DisplayName("Test Update Task")
	class testUpdateTask {
		
		@Test
		@DisplayName("Test Updating Name Only")
		void testUpdateName() {
			service.addTask("1".repeat(10), "Oops", "Must Do");
			service.updateTask("1".repeat(10), "My Task", "");
			assertTrue(service.findTask("1".repeat(10)).getID().equals("1".repeat(10)));
			assertTrue(service.findTask("1".repeat(10)).getName().equals("My Task"));
			assertTrue(service.findTask("1".repeat(10)).getDescription().equals("Must Do"));
		}
		
		@Test
		@DisplayName("Test Updating Description Only")
		void testUpdateDescription() {
			service.addTask("1".repeat(10), "My Task", "Oops");
			service.updateTask("1".repeat(10), "", "Must Do");
			assertTrue(service.findTask("1".repeat(10)).getID().equals("1".repeat(10)));
			assertTrue(service.findTask("1".repeat(10)).getName().equals("My Task"));
			assertTrue(service.findTask("1".repeat(10)).getDescription().equals("Must Do"));
		}
		
		@Test
		@DisplayName("Test Updating Both Fields")
		void testUpdateBoth() {
			service.addTask("1".repeat(10), "Uh Oh", "Oops");
			service.updateTask("1".repeat(10), "My Task", "Must Do");
			assertTrue(service.findTask("1".repeat(10)).getID().equals("1".repeat(10)));
			assertTrue(service.findTask("1".repeat(10)).getName().equals("My Task"));
			assertTrue(service.findTask("1".repeat(10)).getDescription().equals("Must Do"));
		}
		
		@Test
		@DisplayName("Test Updating No Fields")
		void testUpdateNone() {
			service.addTask("1".repeat(10), "Uh Oh", "Oops");
			service.updateTask("1".repeat(10), "", "");
			assertTrue(service.findTask("1".repeat(10)).getID().equals("1".repeat(10)));
			assertTrue(service.findTask("1".repeat(10)).getName().equals("Uh Oh"));
			assertTrue(service.findTask("1".repeat(10)).getDescription().equals("Oops"));
		}
		
		@Test
		@DisplayName("Test Updating Invalid Task")
		void testUpdatingInvalidTask() {
			service.addTask("2".repeat(10), "Uh Oh", "Oops");
			
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				service.updateTask("1".repeat(10), "My Task", "Must Do");
			});
		}
	}

}
