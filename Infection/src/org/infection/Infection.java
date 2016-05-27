package org.infection;

import java.util.ArrayList;
import java.util.Stack;

public class Infection {

	/**
	 * Below are three methods for infecting users (aka changing their site
	 * version from, for example, 1 to 2).
	 * 
	 * In "totalInfection", I take a random user as a starting point and infect
	 * him, all the courses he is taking (or teaching), everyone in each of
	 * those courses, all the courses those users are taking/teaching, so forth.
	 * 
	 * In "limitedInfection", I take a random user as a starting point and
	 * infect him, all the courses he is taking, everyone in each of those
	 * courses, so forth, UNTIL we hit a desired number of infected users, after
	 * which the method returns.
	 * 
	 * "limitedInfectionPercent" is a supporting method which allows us to cap
	 * the number of users infected as a percent of our total number of users,
	 * rather than a number.
	 * 
	 * In general, each of these methods start out with a random user, take all
	 * of his courses, push them on a stack, take all of the users in those
	 * courses, push them on a separate stack, popping them and counting the
	 * users affected until both stacks are empty. All methods return an int for
	 * the total number of participants (students and teachers) infected.
	 */

	public Infection() {
	}

	public int totalInfection(User user, int newVersion) {
		if (user == null) {
			return 0;
		}
		int infectedUserCount = 1;
		int currentVersion = user.getVersion();
		Stack<User> users = new Stack<>();
		Stack<Course> courses = new Stack<>();

		users.push(user);
		user.setVersion(newVersion);

		while (!users.isEmpty() || !courses.isEmpty()) {
			if (!users.isEmpty()) {
				User temp = users.pop();

				ArrayList<Course> coursesParticipating = temp.getCoursesParticipating();

				for (int i = 0; i < coursesParticipating.size(); i++) {
					Course courseParticipating = coursesParticipating.get(i);
					if (courseParticipating.getVersion() == currentVersion) {
						courses.push(courseParticipating);
						courseParticipating.setVersion(newVersion);
					}
				}
			}
			if (!courses.isEmpty()) {
				Course temp = courses.pop();

				ArrayList<User> participants = temp.getParticipants();

				for (int i = 0; i < participants.size(); i++) {
					User participant = participants.get(i);
					if (participant.getVersion() == currentVersion) {
						users.push(participant);
						participant.setVersion(newVersion);
						infectedUserCount++;
					}
				}
			}
		}
		return infectedUserCount;
	}

	public int limitedInfection(User user, int numberToInfect, ArrayList<User> userRoster, int newVersion) {
		if (user == null) {
			return 0;
		}
		int infectedUserCount = 0;
		int currentVersion = user.getVersion();
		Stack<User> users = new Stack<>();
		Stack<Course> courses = new Stack<>();

		// If the target number of users to infect is greater than the total
		// number of users, reset.
		if (numberToInfect > userRoster.size()) {
			numberToInfect = userRoster.size();
		}

		while (infectedUserCount < numberToInfect) {
			users.push(user);

			while (!users.isEmpty() || !courses.isEmpty()) {
				if (!users.isEmpty()) {
					User temp = users.pop();

					ArrayList<Course> coursesParticipating = temp.getCoursesParticipating();

					// In this method, a user is not infected until after his
					// course as been infected. Here, I make a special case for
					// students/teachers not participating in any courses.
					if (coursesParticipating.size() == 0) {
						temp.setVersion(newVersion);
						infectedUserCount++;

						if (infectedUserCount >= numberToInfect) {
							return infectedUserCount;
						}
					} else {
						/**
						 * Something that could be done here is to evaluate the
						 * enrollment size of each class that this user takes,
						 * push them onto a temporary stack, then pop/push them
						 * onto the "courses" stack in order of biggest course
						 * (first in) to smallest course (last in). When the
						 * Limited Infection method hits its target and turns
						 * off, doing so in the smallest class available would
						 * help minimize the disruption. But this might be a lot
						 * of effort for the difference between, say, five
						 * off-version students versus twenty.
						 */
						for (int i = 0; i < coursesParticipating.size(); i++) {
							Course courseParticipating = coursesParticipating.get(i);
							if (courseParticipating.getVersion() == currentVersion) {
								courses.push(courseParticipating);
								courseParticipating.setVersion(newVersion);
							}
						}
					}
				}
				if (!courses.isEmpty()) {
					Course temp = courses.pop();

					ArrayList<User> participants = temp.getParticipants();

					for (int i = 0; i < participants.size(); i++) {
						User participant = participants.get(i);
						if (participant.getVersion() == currentVersion) {
							users.push(participant);
							participant.setVersion(newVersion);
							infectedUserCount++;

							/**
							 * Something else that could be changed is to move
							 * this if statement, below, to just after the
							 * completion of the "for" loop. Then, instead of
							 * the method cutting off abruptly after a certain
							 * target of infected users had been reached, the
							 * method could shut off after the infection
							 * finished reaching all members of the current
							 * course.
							 */
							if (infectedUserCount >= numberToInfect) {
								return infectedUserCount;
							}
						}
					}
				}
			}
			/**
			 * If we didn't hit the infection target with the first infection,
			 * we need to find another infection point.
			 */
			for (User newUser : userRoster) {
				if (newUser.getVersion() == currentVersion) {
					user = newUser;
					break;
				}
			}
		}
		return infectedUserCount;
	}

	public int limitedInfectionPercent(User user, double percentToInfect, ArrayList<User> userRoster, int newVersion) {
		double numberToInfect = userRoster.size() * (percentToInfect / 100);
		int infected = limitedInfection(user, (int) numberToInfect, userRoster, newVersion);
		return infected;
	}
}
