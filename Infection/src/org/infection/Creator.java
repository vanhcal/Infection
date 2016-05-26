package org.infection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.infection.User.UserType;

public class Creator {
	
	// As mentioned in User Class, we aren't very concerned with the differences between students and teachers here 
	// If we needed to, we could add separate structures/getters specifically for teachers and students.
	HashSet<Course> courseSet = new HashSet<>();
	HashSet<User> userSet = new HashSet<>();
	HashMap<Integer, Course> courseMap = new HashMap<>();
	HashMap<Integer, User> userMap = new HashMap<>();
	private final AtomicInteger courses = new AtomicInteger();
	private final AtomicInteger users = new AtomicInteger();
	int [][] coursePairings;
	
	public Creator() {
		newCourses();
		newUsers();
		newUserCoursePairings();
	}
	public void newCourses() {
		addAndMapCourses(new Course ("One", courses.incrementAndGet()));
		addAndMapCourses(new Course ("Two", courses.incrementAndGet()));
		addAndMapCourses(new Course ("Three", courses.incrementAndGet()));
		addAndMapCourses(new Course ("Four", courses.incrementAndGet()));
		addAndMapCourses(new Course ("Five", courses.incrementAndGet()));
		addAndMapCourses(new Course ("Six", courses.incrementAndGet()));
		addAndMapCourses(new Course ("Seven", courses.incrementAndGet()));
		addAndMapCourses(new Course ("Eight", courses.incrementAndGet()));
	}
	public void newUsers() {
		addAndMapUsers(new User ("A", "a", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("B", "b", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("C", "c", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("D", "d", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("E", "e", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("F", "f", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("G", "g", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("H", "h", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("I", "i", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("J", "j", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("K", "k", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("L", "l", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("M", "m", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("N", "n", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("O", "o", UserType.STUDENT, users.incrementAndGet()));
		addAndMapUsers(new User ("X", "x", UserType.STUDENT, users.incrementAndGet()));
		
		addAndMapUsers(new User ("P", "p", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User ("Q", "q", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User ("R", "r", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User ("S", "s", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User ("T", "t", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User ("U", "u", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User ("V", "v", UserType.TEACHER, users.incrementAndGet()));
		addAndMapUsers(new User ("W", "w", UserType.TEACHER, users.incrementAndGet()));
	}
	// we need to be able to keep adding to this; not just initially 
	public void newUserCoursePairings() {
		coursePairings = new int [userSet.size() + 1][courseSet.size() + 1];
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
	// mapping course ID to course name
	// need to be able to catch if we have duplicates
	public void addAndMapCourses(Course course) {
		if (courseSet.add(course) == false) 
		courseMap.put(course.getCourseId(), course);
	}
	// adding user to corresponding sets
	// mapping userID to user name
	public void addAndMapUsers(User user) {
		userSet.add(user);
		userMap.put(user.getUserId(), user);
	}
	// convert to integers and add to both course and user lists
	// throw errors if something can't be matched
	public void addUserToCourse(String lastName, String firstName, String course) {
		int userId = 0;
		User temp = null;
		
		for (User user: userSet) {
			if (user.getLastName().equals(lastName)) {
				if (user.getFirstName().equals(firstName)) {
					// we've found a match 
					userId = user.getUserId();
					temp = user;
					break;
				}
			}
		}
		for (Course courseName: courseSet) {
			if (courseName.getCourseName().equals(course)) {
				// we found the right course
				int courseId = courseName.getCourseId();
				
				// this 2d array indicates which people are taking which class
				// if you're taking a class, this pairing should be represented by your website version number
				// if you're not taking a class, there should be a zero
				coursePairings[userId][courseId] = temp.getVersion();
				
				// don't think these lists are necessary anymore if we do 2d matrix
				// add the student or teacher to the course's list 
				/*
				courseName.getParticipants().add(userId);
				if (temp.userType == UserType.STUDENT) {
					courseName.getStudents().add(userId);
				}
				else if (temp.userType == UserType.TEACHER) {
					courseName.getTeachers().add(userId);
				}
				
				// add the course to the student/teacher's list
				temp.getCoursesParticipating().add(courseId);
				*/
				
				break;
			}
			else {
				// there's no course by that name 
			}
		}
		// there's no one by that name 
	}
	
	// this is pretty slow, but in a real scenario, the random user would be chosen by connecting to the website, 
	// so we would already have a user, and we just apply some percentage chance that they get affected
	public User getRandomUser() {
		int size = userSet.size();
		int rand = new Random().nextInt(size);		
		int count = 0;
		
		for (User user: userSet) {
			if (count == rand) {
				return user;
			}
			else {
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
