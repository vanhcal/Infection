package org.infection;

import org.infection.User.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class InfectionTest {
	
	Infection infection = new Infection();
	ArrayList<User> userRoster = new ArrayList<>();
	ArrayList<Course> courseRoster = new ArrayList<>();
	
	@Before
	public void setup() {
		
		courseRoster.add(new Course("One", 1));
		courseRoster.add(new Course("Two", 2));
		courseRoster.add(new Course("Three", 3));
		courseRoster.add(new Course("Four", 4));
		
		userRoster.add(new User("A", "a", UserType.STUDENT, 1));
		userRoster.add(new User("B", "b", UserType.STUDENT, 2));
		userRoster.add(new User("C", "c", UserType.STUDENT, 3));
		userRoster.add(new User("D", "d", UserType.STUDENT, 4));
		userRoster.add(new User("E", "e", UserType.STUDENT, 5));
		
		addUserToCourse(userRoster.get(0), courseRoster.get(0));
		addUserToCourse(userRoster.get(0), courseRoster.get(1));
		addUserToCourse(userRoster.get(1), courseRoster.get(0));
		addUserToCourse(userRoster.get(1), courseRoster.get(1));
		addUserToCourse(userRoster.get(1), courseRoster.get(2));
		addUserToCourse(userRoster.get(2), courseRoster.get(2));
		addUserToCourse(userRoster.get(3), courseRoster.get(3));
	}
	
	@Test
	public void genericTotalInfection() {
		assertEquals(infection.totalInfection(userRoster.get(0), 2), 3);
		assertEquals(userRoster.get(0).getVersion(), 2);
	}
	
	@Test
	public void isolatedTotalInfection() {
		assertEquals(infection.totalInfection(userRoster.get(3), 2), 1);
		assertEquals(userRoster.get(3).getVersion(), 2);
		assertEquals(userRoster.get(2).getVersion(), 1);
	}
	
	@Test
	public void totalInfectionNull() {
		assertEquals(infection.totalInfection(null, 2), 0);
		assertEquals(userRoster.get(0).getVersion(), 1);
		assertEquals(userRoster.get(3).getVersion(), 1);
	}
	
	public void studentNoClassesTotal() {
		assertEquals(infection.totalInfection(userRoster.get(4), 2), 0);
		assertEquals(userRoster.get(4).getVersion(), 1);
		assertEquals(userRoster.get(3).getVersion(), 1);	
	}
	
	
	@Test
	public void genericLimitedInfection() {
		assertEquals(infection.limitedInfection(userRoster.get(0), 2, userRoster, 2), 2);
		assertEquals(userRoster.get(0).getVersion(), 2);
		assertEquals(userRoster.get(1).getVersion(), 2);
		assertEquals(userRoster.get(2).getVersion(), 1);
		assertEquals(userRoster.get(3).getVersion(), 1);
	}
	
	@Test
	public void limitedInfectionNull() {
		assertEquals(infection.limitedInfection(null, 4, userRoster, 2), 0);
		assertEquals(userRoster.get(0).getVersion(), 1);
		assertEquals(userRoster.get(3).getVersion(), 1);
	}
	
	@Test
	public void studentNoClassesLimited() {
		assertEquals(infection.limitedInfection(userRoster.get(4), 4, userRoster, 2), 4);
		assertEquals(userRoster.get(4).getVersion(), 2);
		assertEquals(userRoster.get(3).getVersion(), 1);	
	}
	
	@Test
	public void infectZeroLimited() {
		assertEquals(infection.limitedInfection(userRoster.get(3), 0, userRoster, 2), 0);
		assertEquals(userRoster.get(1).getVersion(), 1);
		assertEquals(userRoster.get(3).getVersion(), 1);
	}
	
	@Test
	public void infectOneLimited() {
		assertEquals(infection.limitedInfection(userRoster.get(3), 1, userRoster, 2), 1);
		assertEquals(userRoster.get(3).getVersion(), 2);
		assertEquals(userRoster.get(1).getVersion(), 1);
	}
	
	@Test
	public void infectMaxConnectedLimited() {
		assertEquals(infection.limitedInfection(userRoster.get(2), 3, userRoster, 2), 3);
		assertEquals(userRoster.get(2).getVersion(), 2);
		assertEquals(userRoster.get(3).getVersion(), 1);
		assertEquals(userRoster.get(4).getVersion(), 1);
	}
	
	@Test
	public void infectEveryoneLimited() {
		assertEquals(infection.limitedInfection(userRoster.get(2), 5, userRoster, 2), 5);
		assertEquals(userRoster.get(0).getVersion(), 2);
		assertEquals(userRoster.get(3).getVersion(), 2);
		assertEquals(userRoster.get(4).getVersion(), 2);
	}
	
	@Test
	public void infectOvershootLimited() {
		assertEquals(infection.limitedInfection(userRoster.get(2), 10, userRoster, 2), 5);
		assertEquals(userRoster.get(0).getVersion(), 2);
		assertEquals(userRoster.get(3).getVersion(), 2);
		assertEquals(userRoster.get(4).getVersion(), 2);
	}
	
	public void addUserToCourse(User user, Course course) {
		user.getCoursesParticipating().add(course);
		course.getParticipants().add(user);		
	}
}

 