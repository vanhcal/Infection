package org.infection;

import java.util.HashMap;
import java.util.HashSet;

import org.infection.User.UserType;

public class Creator {
	
	HashSet<Course> courseList = new HashSet<>();
	HashSet<User> studentList = new HashSet<>();
	HashSet<User> teacherList = new HashSet<>();
	HashMap<Integer, User> userMap = new HashMap<>();
	HashMap<Integer, Course> courseMap = new HashMap<>();
	
	public Creator() {
		newCourses();
		newUsers();
	}
	public void newCourses() {
		addAndMapCourses(new Course ("Math"));
		addAndMapCourses(new Course ("English"));
		addAndMapCourses(new Course ("Science"));
	}
	public void newUsers() {
		addAndMapUsers(new User ("Bella", "Swan", UserType.STUDENT));
		addAndMapUsers(new User ("Katy", "Perry", UserType.STUDENT));
		addAndMapUsers(new User ("Britney", "Spears", UserType.STUDENT));
		
		addAndMapUsers(new User ("Justin", "Bieber", UserType.STUDENT));
		addAndMapUsers(new User ("Justin", "Timberlake", UserType.STUDENT));
		addAndMapUsers(new User ("Bella", "Hadid", UserType.STUDENT));
		
		addAndMapUsers(new User ("Taylor", "Swift", UserType.STUDENT));
		addAndMapUsers(new User ("Demi", "Lovato", UserType.STUDENT));
		addAndMapUsers(new User ("Selena", "Gomez", UserType.STUDENT));
		
		addAndMapUsers(new User ("Roger", "Moore", UserType.TEACHER));
		addAndMapUsers(new User ("Daniel", "Craig", UserType.TEACHER));
		addAndMapUsers(new User ("Pierce", "Brosnan", UserType.TEACHER));
	}
	public void addAndMapCourses(Course course) {
		courseList.add(course);
		courseMap.put(course.getCourseId(), course);
	}
	public void addAndMapUsers(User user) {
		if (user.userType == UserType.STUDENT) {
			studentList.add(user);
		}
		else if (user.userType == UserType.TEACHER) {
			teacherList.add(user);
		}
		userMap.put(user.getUserId(), user);
	}
}
