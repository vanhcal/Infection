package org.infection;

import java.util.ArrayList;
import java.util.Stack;

public class Infection {

	public Infection() {
	}

	// Starting with a random user, all courses this user takes and all students
	// in those courses, and all courses they're taking, etc., are infected.
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
	// In this method, if we decide to infect 200 users, the method will shut off right after we've hit 200.
	public int limitedInfection(User user, int numberToInfect, ArrayList<User> userRoster, int newVersion) {
		if (user == null) {
			return 0;
		}
		
		int infectedUserCount = 0;
		int currentVersion = user.getVersion();
		Stack<User> users = new Stack<>();
		Stack<Course> courses = new Stack<>();
		
		// If the number of users to infect is greater than the total number of
		// users, reset.
		if (numberToInfect > userRoster.size()) {
			numberToInfect = userRoster.size();
		}
		
		while (infectedUserCount < numberToInfect) {
			// Patient 0 is not infected until his classroom gets infected (thereby infecting him)/
			// TODO: a student with no classes should still be considered to be infected
			users.push(user);

			while (!users.isEmpty() || !courses.isEmpty()) {
				if (!users.isEmpty()) {
					User temp = users.pop();

					ArrayList<Course> coursesParticipating = temp.getCoursesParticipating();
					
					if (coursesParticipating.size() == 0) {
						temp.setVersion(newVersion);
						infectedUserCount++;
						
						if (infectedUserCount >= numberToInfect) {
							return infectedUserCount;
						}
					}
					else {
						for (int i = 0; i < coursesParticipating.size(); i++) {
							Course courseParticipating = coursesParticipating.get(i);
							if (courseParticipating.getVersion() == currentVersion) {
								courses.push(courseParticipating);
							}
						}
					}
				}
				if (!courses.isEmpty()) {
					// TODO: get min
					Course temp = courses.pop();

					ArrayList<User> participants = temp.getParticipants();

					for (int i = 0; i < participants.size(); i++) {
						User participant = participants.get(i);
						if (participant.getVersion() == currentVersion) {
							users.push(participant);
							participant.setVersion(newVersion);
							infectedUserCount++;
							
							if (infectedUserCount >= numberToInfect) {
								return infectedUserCount;
							}
						}
					}
				}
			}
			// If we didn't hit the infection target with the first infection,
			// we need to find another infection point.
			for (User newUser : userRoster) {
				if (newUser.getVersion() == currentVersion) {
					user = newUser;
					break;
				}
			}
		}
		return infectedUserCount;
	}
}
