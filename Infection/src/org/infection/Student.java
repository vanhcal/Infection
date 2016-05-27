package org.infection;

public class Student extends User {

	public Student(String firstName, String lastName, int userId) {
		super(firstName, lastName, UserType.STUDENT, userId);
	}
	
}
