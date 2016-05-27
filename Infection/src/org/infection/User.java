package org.infection;

import java.util.ArrayList;

public class User {

	/**
	 * The User class contains the user's first name, last name, user type (whether
	 * they are a teacher or student), a userID (which is set in Creator), and
	 * an ArrayList of the all the courses the user is either enrolled in as a
	 * student or teaching as a teacher.
	 * 
	 * There are inherited "Student" and "Teacher" classes for future methods
	 * where the two can be further distinguished if necessary. But in this
	 * program they are treated similarly, so the two are not further fleshed
	 * out.
	 * 
	 * The website version number in the constructor currently starts at 1 for
	 * all new users. This can be changed via the setVersion method, or via one
	 * of the Infection methods.
	 */

	private final int userId;
	private final String firstName, lastName;
	private final UserType userType;
	private int version;
	private final ArrayList<Course> coursesParticipating;

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

	public void addCourseParticipating(Course course) {
		coursesParticipating.add(course);
	}

	public ArrayList<Course> getCoursesParticipating() {
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
