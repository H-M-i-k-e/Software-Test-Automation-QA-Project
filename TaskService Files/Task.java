package taskPackage;

public class Task {
	
	private String taskID;
	private String name;
	private String description;
	
	public Task(String newTaskID, String newName, String newDescription) {
		if (newTaskID == null || newTaskID.length() > 10) {
			throw new IllegalArgumentException("Invalid Task ID");
		}
		
		this.taskID = newTaskID;
		setName(newName);
		setDescription(newDescription);
	}
	
	public void setName(String newName) {
		if (newName == null || newName.length() > 20) {
			throw new IllegalArgumentException("Invalid Task Name");
		}
		this.name = newName;
	}
	
	public void setDescription(String newDescription) {
		if (newDescription == null || newDescription.length() > 50) {
			throw new IllegalArgumentException("Invalid Task Name");
		}
		this.description = newDescription;
	}
	
	public String getID() {  return taskID;  }
	public String getName() {  return name;  }
	public String getDescription() {  return description;  }
}
