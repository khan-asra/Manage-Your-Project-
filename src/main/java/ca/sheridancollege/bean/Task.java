package ca.sheridancollege.bean;



import lombok.Data;

@Data
public class Task {
	private String taskTitle;
	private long taskId;
	private long memberId;
	private String taskHolder;
	private String task;
	private final String [] taskStatus= {"In-Complete","Complete","In Progress", "Cancelled"};

	
	
	
}
