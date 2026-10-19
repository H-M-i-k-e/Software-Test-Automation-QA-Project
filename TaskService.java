package taskPackage;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
	
	private Map<String, Task> taskList = new HashMap<>();
	
	public TaskService() {
	}
	
	public Task findTask(String targetID) {
		if (taskList.get(targetID) != null) {
			return taskList.get(targetID);
		}
		return null;
	}
	
	public void addTask(String taskID, String name, String description) {
		if (findTask(taskID) != null) {
			throw new IllegalArgumentException("Task Already Exists");
		}
		taskList.put(taskID, new Task(taskID, name, description));
	}
	
	public void deleteTask(String targetID) {
		if (findTask(targetID) == null) {
			throw new IllegalArgumentException("Task Does Not Exist");
		}
		taskList.remove(targetID);
	}
	
	public void updateTask(String targetID, String newName, String newDescription) {
		Task targetTask = findTask(targetID);
		
		if (targetTask == null) {
			throw new IllegalArgumentException("Task Does Not Exist");
		}
		
		if (newName != "") {
			targetTask.setName(newName);
		}
		if (newDescription != "") {
			targetTask.setDescription(newDescription);
		}
	}

}
