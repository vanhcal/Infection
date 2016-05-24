package org.infection;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
	// probably need some way to read in what the use version is 
	// http://stackoverflow.com/questions/690419/build-and-version-numbering-for-java-projects-ant-cvs-hudson
	
	private final AtomicInteger count = new AtomicInteger(0);
	private final int userId;
	String firstName, lastName;
	int version;
	UserType userType;
	
	enum UserType {
		TEACHER, STUDENT;
	}
	
	public User(String firstName, String lastName, UserType userType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		version = 1; 
		userId = count.incrementAndGet(); 
	}
	public void setVersion(int nVersion) {
		version = nVersion;
	}
	public int getVersion() { 
		return version;
	}
	public int getUserId() {
		return userId;
	}
}
