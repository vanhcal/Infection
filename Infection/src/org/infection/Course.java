package org.infection;

import java.util.ArrayList;

public class Course {
	
	private final int courseId;
	private final String courseName;
	// We only use a participants list here 
	// (to represent both the teacher(s) teaching a class and the students enrolled) 
	// But separate teacher and student lists could be constructed.
	ArrayList<Integer> participants;
	
	public Course(String courseName, int courseId) {
		this.courseName = courseName;
		this.courseId = courseId;
		participants = new ArrayList<>();
	}
	public void addParticipant(int participant) {
		participants.add(participant);
	}
	public ArrayList<Integer> getParticipants() {
		return participants;
	}
	public int getCourseId() {
		return courseId;
	}
	public String getCourseName() {
		return courseName;
	}
}
