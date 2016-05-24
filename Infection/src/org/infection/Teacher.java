package org.infection;

import java.util.ArrayList;


public class Teacher extends User {
	
	ArrayList<Integer> coursesTeaching;

	public Teacher(String firstName, String lastName, UserType userType) {
		super(firstName, lastName, userType);
		coursesTeaching = new ArrayList<>();
	}
	public void addCourse(int course) {
		coursesTeaching.add(course);
	}
	public ArrayList<Integer> getCourses() {
		return coursesTeaching;
	}
}
