package com.erpsystem.crms.model;

public class TaskModel {

	private long taskid;
	private String task_title;
	private long assignto;
	private String description;
	private String expdate;
	public long getTaskid() {
		return taskid;
	}
	public void setTaskid(long taskid) {
		this.taskid = taskid;
	}
	
	public String getTask_title() {
		return task_title;
	}
	public void setTask_title(String task_title) {
		this.task_title = task_title;
	}
	public long getAssignto() {
		return assignto;
	}
	public void setAssignto(long assignto) {
		this.assignto = assignto;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExpdate() {
		return expdate;
	}
	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}
	
	
	
}
