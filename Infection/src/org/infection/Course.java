package org.infection;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Course {
	
	private final AtomicInteger count = new AtomicInteger(0);
	private final int courseID;
	String courseName;
	ArrayList<Integer> students;
	
	public Course(String courseName) {
		this.courseName = courseName;
		courseID = count.incrementAndGet(); 
		students = new ArrayList<>();
	}
	public void addStudent(int student) {
		students.add(student);
	}
	public int getCourseId() {
		return courseID;
	}
}
