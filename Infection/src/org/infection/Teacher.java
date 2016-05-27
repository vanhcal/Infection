package org.infection;


public class Teacher extends User {
	
	public Teacher(String firstName, String lastName, int userId) {
		super(firstName, lastName, UserType.TEACHER, userId);
	}
}
