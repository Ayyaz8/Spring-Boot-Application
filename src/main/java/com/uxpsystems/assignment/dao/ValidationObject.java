package com.uxpsystems.assignment.dao;

public class ValidationObject {

	private boolean wasValidStatus = true;
	private boolean userDoesnotExists = false;

	public boolean isWasValidStatus() {
		return wasValidStatus;
	}

	public void setWasValidStatus(boolean wasValidStatus) {
		this.wasValidStatus = wasValidStatus;
	}

	public boolean isUserDoesnotExists() {
		return userDoesnotExists;
	}

	public void setUserDoesnotExists(boolean userDoesnotExists) {
		this.userDoesnotExists = userDoesnotExists;
	}

}
