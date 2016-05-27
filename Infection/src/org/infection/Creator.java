package org.infection;

import java.util.ArrayList;
import java.util.Random;

import org.infection.User.UserType;

public class Creator {

	// As mentioned in User Class, we aren't very concerned with the differences
	// between students and teachers here. If we needed to, we could add
	// separate structures/getters specifically for teachers and students.
	ArrayList<Course> courseRoster = new ArrayList<>();
	ArrayList<User> userRoster = new ArrayList<>();
	private int courses = 0;
	private int users = 0;

	public Creator() {
		newCourses();
		newUsers();
		newUserCoursePairings();
	}

	public void newCourses() {
		addCourseToRoster(new Course("One", ++courses));
		addCourseToRoster(new Course("Two", ++courses));
		addCourseToRoster(new Course("Three", ++courses));
		addCourseToRoster(new Course("Four", ++courses));
		addCourseToRoster(new Course("Five", ++courses));
		addCourseToRoster(new Course("Six", ++courses));
		addCourseToRoster(new Course("Seven", ++courses));
		addCourseToRoster(new Course("Eight", ++courses));
	}

	public void newUsers() {
		addUserToRoster(new User("A", "a", UserType.STUDENT, ++users));
		addUserToRoster(new User("B", "b", UserType.STUDENT, ++users));
		addUserToRoster(new User("C", "c", UserType.STUDENT, ++users));
		addUserToRoster(new User("D", "d", UserType.STUDENT, ++users));
		addUserToRoster(new User("E", "e", UserType.STUDENT, ++users));
		addUserToRoster(new User("F", "f", UserType.STUDENT, ++users));
		addUserToRoster(new User("G", "g", UserType.STUDENT, ++users));
		addUserToRoster(new User("H", "h", UserType.STUDENT, ++users));
		addUserToRoster(new User("I", "i", UserType.STUDENT, ++users));
		addUserToRoster(new User("J", "j", UserType.STUDENT, ++users));
		addUserToRoster(new User("K", "k", UserType.STUDENT, ++users));
		addUserToRoster(new User("L", "l", UserType.STUDENT, ++users));
		addUserToRoster(new User("M", "m", UserType.STUDENT, ++users));
		addUserToRoster(new User("N", "n", UserType.STUDENT, ++users));
		addUserToRoster(new User("O", "o", UserType.STUDENT, ++users));
		addUserToRoster(new User("X", "x", UserType.STUDENT, ++users));

		addUserToRoster(new User("P", "p", UserType.TEACHER, ++users));
		addUserToRoster(new User("Q", "q", UserType.TEACHER, ++users));
		addUserToRoster(new User("R", "r", UserType.TEACHER, ++users));
		addUserToRoster(new User("S", "s", UserType.TEACHER, ++users));
		addUserToRoster(new User("T", "t", UserType.TEACHER, ++users));
		addUserToRoster(new User("U", "u", UserType.TEACHER, ++users));
		addUserToRoster(new User("V", "v", UserType.TEACHER, ++users));
		addUserToRoster(new User("W", "w", UserType.TEACHER, ++users));
	}
	
	public void newUserCoursePairings() {
		addUserToCourse(userRoster.get(0), courseRoster.get(0));
		addUserToCourse(userRoster.get(0), courseRoster.get(2));
		addUserToCourse(userRoster.get(0), courseRoster.get(4));
		addUserToCourse(userRoster.get(1), courseRoster.get(0));
		addUserToCourse(userRoster.get(1), courseRoster.get(1));
		addUserToCourse(userRoster.get(1), courseRoster.get(2));
		addUserToCourse(userRoster.get(1), courseRoster.get(3));
		addUserToCourse(userRoster.get(2), courseRoster.get(0));
		addUserToCourse(userRoster.get(2), courseRoster.get(1));
		addUserToCourse(userRoster.get(2), courseRoster.get(2));
		addUserToCourse(userRoster.get(2), courseRoster.get(3));
		addUserToCourse(userRoster.get(2), courseRoster.get(4));
		addUserToCourse(userRoster.get(3), courseRoster.get(0));
		addUserToCourse(userRoster.get(3), courseRoster.get(3));
		addUserToCourse(userRoster.get(3), courseRoster.get(4));
		addUserToCourse(userRoster.get(4), courseRoster.get(0));
		addUserToCourse(userRoster.get(4), courseRoster.get(1));
		addUserToCourse(userRoster.get(5), courseRoster.get(1));
		addUserToCourse(userRoster.get(5), courseRoster.get(4));
		addUserToCourse(userRoster.get(6), courseRoster.get(1));
		addUserToCourse(userRoster.get(6), courseRoster.get(2));
		addUserToCourse(userRoster.get(6), courseRoster.get(4));
		addUserToCourse(userRoster.get(6), courseRoster.get(6));
		addUserToCourse(userRoster.get(7), courseRoster.get(2));
		addUserToCourse(userRoster.get(7), courseRoster.get(3));
		addUserToCourse(userRoster.get(7), courseRoster.get(6));
		addUserToCourse(userRoster.get(8), courseRoster.get(3));
		addUserToCourse(userRoster.get(9), courseRoster.get(5));
		addUserToCourse(userRoster.get(10), courseRoster.get(5));
		addUserToCourse(userRoster.get(11), courseRoster.get(5));
		addUserToCourse(userRoster.get(12), courseRoster.get(5));
		addUserToCourse(userRoster.get(12), courseRoster.get(6));
		addUserToCourse(userRoster.get(13), courseRoster.get(5));
		addUserToCourse(userRoster.get(13), courseRoster.get(6));
		addUserToCourse(userRoster.get(14), courseRoster.get(6));
		addUserToCourse(userRoster.get(15), courseRoster.get(0));
		addUserToCourse(userRoster.get(16), courseRoster.get(1));
		addUserToCourse(userRoster.get(17), courseRoster.get(2));
		addUserToCourse(userRoster.get(18), courseRoster.get(3));
		addUserToCourse(userRoster.get(19), courseRoster.get(4));
		addUserToCourse(userRoster.get(20), courseRoster.get(5));
		addUserToCourse(userRoster.get(21), courseRoster.get(6));
		addUserToCourse(userRoster.get(22), courseRoster.get(8));
		addUserToCourse(userRoster.get(23), courseRoster.get(8));
	}
	
	public void addCourseToRoster(Course course) {
		for (int i = 0; i < courseRoster.size(); i++) {
			if (courseRoster.get(i).equals(course)) {
				throw new DuplicateException(course.getCourseName() + " is a duplicate course.");
			}
		}
		courseRoster.add(course);
	}
	
	public void addUserToRoster(User user) {
		for (int i = 0; i < userRoster.size(); i++) {
			if (userRoster.get(i).equals(user)) {
				throw new DuplicateException(user.getName() + " is a duplicate user.");
			}
		}
		userRoster.add(user);
	}

	public void addUserToCourse(User user, Course course) {
		user.getCoursesParticipating().add(course);
		course.getParticipants().add(user);		
	}

	// This is a pretty slow method of finding a random user. But presumably in
	// a real scenario we'd already have a random user (the one connecting to
	// the website).
	public User getRandomUser() {
		int size = userRoster.size();
		int rand = new Random().nextInt(size);
		int count = 0;

		for (User user : userRoster) {
			if (count == rand) {
				return user;
			} 
			else {
				count++;
			}
		}
		return null;
	}
	
	public ArrayList<User> getUserRoster() {
		return userRoster;
	}
	
	public ArrayList<Course> getCourseRoster() {
		return courseRoster;
	}
}
