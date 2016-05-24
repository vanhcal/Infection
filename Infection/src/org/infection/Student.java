package org.infection;

import java.util.ArrayList;

public class Student extends User {
	
	ArrayList<Integer> coursesTaking;

	public Student(String firstName, String lastName, UserType userType) {
		super(firstName, lastName, userType);
		coursesTaking = new ArrayList<>();
	}
	public void addCourse(int course) { 
		coursesTaking.add(course);
	}
	public ArrayList<Integer> getCourses() {
		return coursesTaking;
	}
}
