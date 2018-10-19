package com.erpsystem.crms.controller;

public class AccessList {

	
	private long entity;
	
	private long branch;
	private long activity;
	
	public AccessList(long entity, long branch, long activity) {
		super();
		this.entity = entity;
		this.branch = branch;
		this.activity = activity;
	}
	public long getEntity() {
		return entity;
	}
	public void setEntity(long entity) {
		this.entity = entity;
	}
	public long getBranch() {
		return branch;
	}
	public void setBranch(long branch) {
		this.branch = branch;
	}
	public long getActivity() {
		return activity;
	}
	public void setActivity(long activity) {
		this.activity = activity;
	}
	
	
	}
