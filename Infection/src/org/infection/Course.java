package org.infection;

import java.util.ArrayList;

public class Course {

	/**
	 * The Course class contains the course's name, courseID (set in Creator),
	 * and an ArrayList of all the users (teachers and students) participating
	 * in the class. If desired, separate lists for teachers and students could
	 * be constructed.
	 * 
	 * Each course also has a website version number. If any participant in the
	 * course has been infected with a different website version, this course
	 * version number will reflect that new version.
	 */
	
	private final int courseId;
	private final String courseName;
	private final ArrayList<User> participants;
	private int version;

	public Course(String courseName, int courseId) {
		this.courseName = courseName;
		this.courseId = courseId;
		participants = new ArrayList<>();
		version = 1;
	}

	public void addParticipant(User participant) {
		participants.add(participant);
	}

	public ArrayList<User> getParticipants() {
		return participants;
	}

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setVersion(int nVersion) {
		version = nVersion;
	}

	public int getVersion() {
		return version;
	}
}
