package org.infection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.infection.User.UserType;

public class Creator {

	// As mentioned in User Class, we aren't very concerned with the differences
	// between students and teachers here. If we needed to, we could add
	// separate structures/getters specifically for teachers and students.
	HashSet<Course> courseSet = new HashSet<>();
	HashSet<User> userSet = new HashSet<>();
	HashMap<Integer, Course> courseMap = new HashMap<>();
	HashMap<Integer, User> userMap = new HashMap<>();
	private final AtomicInteger courses = new AtomicInteger();
	private final AtomicInteger users = new AtomicInteger();
	int[][] coursePairings;

	public Creator() {
		newCourses();
		newUsers();
		newUserCoursePairings();
	}

	public void newCourses() {
		addAndMapCourses(new Course("One", courses.incrementAndGet()));
		addAndMapCourses(new Course("Two", courses.incrementAndGet()));
		addAndMapCourses(new Course("Three", courses.incrementAndGet()));
		addAndMapCourses(new Course("Four", courses.incrementAndGet()));
		addAndMapCourses(new Course("Five", courses.incrementAndGet()));
		addAndMapCourses(new Course("Six", courses.incrementAndGet()));
		addAndMapCourses(new Course("Seven", courses.incrementAndGet()));
		addAndMapCourses(new Course("Eight", courses.incrementAndGet()));
	}

	public void newUsers() {
		addAndMapUsers(new User("A", "a", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("B", "b", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("C", "c", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("D", "d", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("E", "e", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("F", "f", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("G", "g", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("H", "h", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("I", "i", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("J", "j", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("K", "k", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("L", "l", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("M", "m", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("N", "n", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("O", "o", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User("X", "x", UserType.STUDENT, users.incrementAndGet()));

		addAndMapUsers(new User("P", "p", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User("Q", "q", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User("R", "r", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User("S", "s", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User("T", "t", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User("U", "u", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User("V", "v", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User("W", "w", UserType.TEACHER, users.incrementAndGet()));
	}

	// TODO: we need to be able to keep adding to this; not just initially
	public void newUserCoursePairings() {
		coursePairings = new int[userSet.size() + 1][courseSet.size() + 1];
		addUserToCourse("a", "A", "One");
		addUserToCourse("a", "A", "Three");
		addUserToCourse("a", "A", "Five");
		addUserToCourse("b", "B", "One");
		addUserToCourse("b", "B", "Two");
		addUserToCourse("b", "B", "Three");
		addUserToCourse("b", "B", "Four");
		addUserToCourse("c", "C", "One");
		addUserToCourse("c", "C", "Two");
		addUserToCourse("c", "C", "Three");
		addUserToCourse("c", "C", "Four");
		addUserToCourse("c", "C", "Five");
		addUserToCourse("d", "D", "One");
		addUserToCourse("d", "D", "Four");
		addUserToCourse("d", "D", "Five");
		addUserToCourse("e", "E", "One");
		addUserToCourse("e", "E", "Two");
		addUserToCourse("f", "F", "Two");
		addUserToCourse("f", "F", "Five");
		addUserToCourse("g", "G", "Two");
		addUserToCourse("g", "G", "Three");
		addUserToCourse("g", "G", "Five");
		addUserToCourse("g", "G", "Seven");
		addUserToCourse("h", "H", "Three");
		addUserToCourse("h", "H", "Four");
		addUserToCourse("h", "H", "Seven");
		addUserToCourse("i", "I", "Four");
		addUserToCourse("j", "J", "Six");
		addUserToCourse("k", "K", "Six");
		addUserToCourse("l", "L", "Six");
		addUserToCourse("m", "M", "Six");
		addUserToCourse("m", "M", "Seven");
		addUserToCourse("n", "N", "Six");
		addUserToCourse("n", "N", "Seven");
		addUserToCourse("o", "O", "Seven");
		addUserToCourse("p", "P", "One");
		addUserToCourse("q", "Q", "Two");
		addUserToCourse("r", "R", "Three");
		addUserToCourse("s", "S", "Four");
		addUserToCourse("t", "T", "Five");
		addUserToCourse("u", "U", "Six");
		addUserToCourse("v", "V", "Seven");
		addUserToCourse("w", "W", "Eight");
		addUserToCourse("x", "X", "Eight");
	}

	// TODO: need to be able to catch if we have duplicates
	public void addAndMapCourses(Course course) {
		courseSet.add(course);
		courseMap.put(course.getCourseId(), course);
	}

	public void addAndMapUsers(User user) {
		userSet.add(user);
		userMap.put(user.getUserId(), user);
	}

	public void addUserToCourse(String lastName, String firstName, String course) {
		int userId = 0;
		User temp = null;

		// Take a person that we've added to a class, and confirm that they are
		// a user.
		for (User user : userSet) {
			if (user.getLastName().equals(lastName)) {
				if (user.getFirstName().equals(firstName)) {
					userId = user.getUserId();
					temp = user;
					break;
				}
			}
		}
		// Take a course we've added users to, and confirm that it is one of our
		// courses.
		for (Course courseName : courseSet) {
			if (courseName.getCourseName().equals(course)) {
				int courseId = courseName.getCourseId();
				// In this 2D array, students and teachers in any given course
				// are marked in the matrix by their website version. If a
				// student/teacher is not taking a certain class, it is marked
				// in the matrix by a '0'.
				coursePairings[userId][courseId] = temp.getVersion();

				// Add the courses and participants to each other's lists in
				// case we need to find a quick way to (for example) see what
				// courses a student is taking.
				temp.getCoursesParticipating().add(courseId);
				courseName.getParticipants().add(userId);
				
				break;
			} 
		}
		// TODO: what if there's no course or user by that name?
	}

	// This is a pretty slow method of finding a random user. But presumably in
	// a real scenario we'd already have a random user (the one connecting to
	// the website).
	public User getRandomUser() {
		int size = userSet.size();
		int rand = new Random().nextInt(size);
		int count = 0;

		for (User user : userSet) {
			if (count == rand) {
				return user;
			} else {
				count++;
			}
		}
		return null;
	}

	public HashMap<Integer, Course> getCourseMap() {
		return courseMap;
	}

	public HashMap<Integer, User> getUserMap() {
		return userMap;
	}

	public int[][] getCoursePairings() {
		return coursePairings;
	}
}
