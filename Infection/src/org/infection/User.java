package org.infection;

import java.util.ArrayList;

public class User {
	// TODO: how do we know what the version number is? Do we need to read it in from somewhere?
	// http://stackoverflow.com/questions/690419/build-and-version-numbering-for-java-projects-ant-cvs-hudson

	// There are inherited "Student" and "Teacher" classes for future methods
	// were the two need to be further distinguished. But in this program they
	// are treated similarly, so the two are not further fleshed out.
	private final int userId;
	private final String firstName, lastName;
	private final UserType userType;
	int version;
	// This ArrayList, and the corresponding one in the Course class, is not
	// used in the infect method. But it's included because otherwise it would
	// be really hard (slow) to find out which classes a student was taking, or
	// which students were in a class.
	ArrayList<Integer> coursesParticipating;

	enum UserType {
		TEACHER, STUDENT;
	}

	public User(String firstName, String lastName, UserType userType, int userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.userId = userId;
		version = 1;
		coursesParticipating = new ArrayList<>();
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

	public void addCourseParticipating(int course) {
		coursesParticipating.add(course);
	}

	public ArrayList<Integer> getCoursesParticipating() {
		return coursesParticipating;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getName() {
		if (this.userType == UserType.TEACHER) {
			return lastName + ", " + firstName + "*";
		}
		return lastName + ", " + firstName;
	}
}
