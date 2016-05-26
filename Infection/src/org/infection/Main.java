package org.infection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Creator creator = new Creator();
		HashMap<Integer, User> userMap = creator.getUserMap();
		int[][] coursePairings = creator.getCoursePairings();
		User user = creator.getRandomUser();
		int userId = user.getUserId();
		int currentVersion = user.getVersion();
		int newVersion = 2;

		System.out.println("Patient 0: " + user.getName());
		int infectedLimited = limitedInfectionNumberExact(14, userId, currentVersion, newVersion, userMap,
				coursePairings);
		System.out.println("We have infected " + infectedLimited + " users.");
		printArray(coursePairings);
	}

	// View the matrix of students/teachers to courses.
	public static void printArray(int[][] coursePairings) {
		for (int i = 1; i < coursePairings.length; i++) {
			for (int j = 1; j < coursePairings[0].length; j++) {
				System.out.print(coursePairings[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

	// View the printout of all students, teachers, and what courses they're in.
	public static void printRoster(int[][] coursePairings, HashMap<Integer, Course> courseMap,
			HashMap<Integer, User> userMap) {
		for (int i = 1; i < coursePairings.length; i++) {
			for (int j = 1; j < coursePairings[0].length; j++) {
				if (coursePairings[i][j] == 1) {
					System.out.print(userMap.get(i).getName());
					System.out.print(", ");
					System.out.println(courseMap.get(j).getCourseName());
				}
			}
		}
	}

	// Starting with a random user, all courses this user takes and all students
	// in those courses, and all courses they're taking, etc., are infected.
	public static int totalInfection(int userId, int currentVersion, int newVersion, HashMap<Integer, User> userMap,
			int[][] coursePairings) {
		int infectedUserCount = 1;
		Stack<Integer> users = new Stack<>();
		Stack<Integer> courses = new Stack<>();
		HashSet<Integer> infectedUsers = new HashSet<>();

		users.push(userId);
		infectedUsers.add(userId);

		while (!users.isEmpty() || !courses.isEmpty()) {
			if (!users.isEmpty()) {
				int i = users.pop();

				for (int j = 1; j < coursePairings[0].length; j++) {
					if (coursePairings[i][j] == currentVersion) {
						coursePairings[i][j] = newVersion;
						courses.push(j);
					}
				}
			}
			if (!courses.isEmpty()) {
				int j = courses.pop();

				for (int i = 0; i < coursePairings.length; i++) {
					if (coursePairings[i][j] == currentVersion) {
						coursePairings[i][j] = newVersion;
						userMap.get(i).setVersion(newVersion);
						users.push(i);

						if (!infectedUsers.contains(i)) {
							infectedUsers.add(i);
							infectedUserCount++;
						}
					}
				}
			}
		}
		return infectedUserCount;
	}

	// In this method, if we decide to infect 200 users, the method will shut
	// off right after we've hit 200.
	// One way this method could be improved is if it compared classroom size
	// before deciding which classroom to infect next. However, we'd probably
	// need a faster data structure than a 2D array.
	// Alternately, if we put the first "return" outside of its above for loop,
	// we could hit a target number and then finish infecting that specific
	// classroom (rather than shutting off the infection immediately.)
	public static int limitedInfectionNumberExact(int numberToInfect, int userId, int currentVersion, int newVersion,
			HashMap<Integer, User> userMap, int[][] coursePairings) {
		int infectedUserCount = 0;
		Queue<Integer> users = new LinkedList<Integer>();
		Queue<Integer> courses = new LinkedList<Integer>();
		HashSet<Integer> infectedUsers = new HashSet<>();

		// If the number of users to infect is greater than the total number of
		// users, reset.
		if (numberToInfect > userMap.size()) {
			numberToInfect = userMap.size();
		}

		while (infectedUserCount < numberToInfect) {
			users.add(userId);

			while (!users.isEmpty() || !courses.isEmpty()) {
				// If there are courses on the queue, pop the course and start
				// infecting its participants (teachers and students) one by
				// one. If a participant has not already been infected, increase
				// the count. Change all of that participant's values in the
				// matrix to equal the new website version. Add all of the
				// participant's courses onto the queue. If that participant
				// allows us to meet the infection target, stop the method.
				while (!courses.isEmpty()) {
					int j = courses.remove();

					for (int i = 1; i < coursePairings.length; i++) {
						if (coursePairings[i][j] == currentVersion) {
							coursePairings[i][j] = newVersion;
							userMap.get(i).setVersion(newVersion);
							users.add(i);

							if (!infectedUsers.contains(i)) {
								infectedUsers.add(i);
								infectedUserCount++;

								for (int k = 1; k < coursePairings[0].length; k++) {
									if (coursePairings[i][k] == currentVersion) {
										coursePairings[i][k] = newVersion;
										courses.add(k);
									}
								}

								if (infectedUserCount == numberToInfect) {
									return infectedUserCount;
								}
							}
						}
					}
				}
				if (!users.isEmpty()) {
					int i = users.remove();

					if (!infectedUsers.contains(i)) {
						infectedUsers.add(i);
						infectedUserCount++;

						for (int j = 1; j < coursePairings[0].length; j++) {
							if (coursePairings[i][j] == currentVersion) {
								coursePairings[i][j] = newVersion;
								courses.add(j);
							}
						}
						if (infectedUserCount == numberToInfect) {
							return infectedUserCount;
						}
					}
				}
			}

			// If we didn't hit the infection target with the first infection,
			// we need to find another infection point.
			for (Integer i : userMap.keySet()) {
				if (!infectedUsers.contains(i)) {
					userId = i;
				}
			}
		}
		return infectedUserCount;
	}

	// We can decide we want to infect a percentage of our users, rather than a
	// straight number.
	public static int limitedInfectionPercentExact(double percent, int userId, int currentVersion, int newVersion,
			HashMap<Integer, User> userMap, int[][] coursePairings) {
		int numberToInfect = (int) (userMap.size() * (percent / 100));
		int nInfected = limitedInfectionNumberExact(numberToInfect, userId, currentVersion, newVersion, userMap,
				coursePairings);
		return nInfected;
	}
}
