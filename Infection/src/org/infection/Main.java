package org.infection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

	public static void main (String [] args) {
		Creator creator = new Creator();
		HashMap<Integer,Course> courseMap = creator.getCourseMap();
		HashMap<Integer, User> userMap = creator.getUserMap();
		int[][] coursePairings = creator.getCoursePairings();
		User user = creator.getRandomUser();
		System.out.println("Patient 0: " + user.getName());
		
		//printRoster(coursePairings, courseMap, userMap);
		//int infected = totalInfection(user, userMap, coursePairings);
		
		// TODO: finish the other limited method
		
		int infected = limitedInfectionNumberExact(25, user, userMap, coursePairings);
		System.out.println("We have infected " + infected + " users.");
		printArray(coursePairings);
		
	}
	public static void printArray(int[][] coursePairings) {
		for (int i = 1; i < coursePairings.length; i++) {
			for (int j = 1; j < coursePairings[0].length; j++) {
				System.out.print(coursePairings[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	public static void printRoster(int[][] coursePairings, HashMap<Integer,Course> courseMap, HashMap<Integer, User> userMap) {
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
	// print out how many total people end up infected
	public static int totalInfection(User user, HashMap<Integer, User> userMap, int[][] coursePairings) {
		int infectedUserCount = 1;
		int userId = user.getUserId();
		int currentVersion = user.getVersion();
		int newVersion = 2; 
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
						courses.push(j);
						coursePairings[i][j] = newVersion;
					}
				}
			}
			if (!courses.isEmpty()) {
				int j = courses.pop();
				
				for (int i = 0; i < coursePairings.length; i++) {
					if (coursePairings[i][j] == currentVersion) {
						users.push(i);
						coursePairings[i][j] = newVersion;
						userMap.get(i).setVersion(newVersion);
						
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
	// this method finishes off a classroom when we hit a target
	public void limitedInfectionNumber (int target) {
		/*
		 * start with user 
		 * push all their courses into a queue
		   one by one, pop the course
		 */
		
	}
	// you can make this better by infecting smaller classrooms first
	// that involves iterating through the matrix, or using a parallel structure
	// with limited infection, do you finish the courses that have already been partly affected
	// or make sure that students 
	public static int limitedInfectionNumberExact (int cap, User user, HashMap<Integer, User> userMap, int[][] coursePairings) {
		int infectedUserCount = 0;
		int userId = user.getUserId();
		int currentVersion = user.getVersion();
		int newVersion = 2; 
		HashSet<Integer> infectedUsers = new HashSet<>();
		Queue<Integer> users = new LinkedList<Integer>();
		Queue<Integer> courses = new LinkedList<Integer>();
		
		// resetting the cap if it's larger than the user size
		if (cap > userMap.size()) {
			cap = userMap.size();
		}
		
		while (infectedUserCount < cap) {
			
			users.add(userId);
			while (!users.isEmpty() || !courses.isEmpty()) {
				while (!courses.isEmpty()) {
					int j = courses.remove();
					
					// after popping a course, we're marking all the users and adding them to the infected count
					for (int i = 1; i < coursePairings.length; i++) {
						if (coursePairings[i][j] == currentVersion) {
							coursePairings[i][j] = newVersion;
							users.add(i);
							userMap.get(i).setVersion(newVersion);
							
							if (!infectedUsers.contains(i)) {
								infectedUsers.add(i);
								infectedUserCount++;
								
								// mark all of the newly infected user's classes
								for (int k = 1; k < coursePairings[0].length; k++) {
									if (coursePairings[i][k] == currentVersion) {
										coursePairings[i][k] = newVersion;
										courses.add(k);
									}
								}
								
								if (infectedUserCount == cap) {
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
						if (infectedUserCount == cap) {
							return infectedUserCount;
						}
					}
				}
			}
			// if we haven't hit the cap and need to find another infection point
			for (Integer i: userMap.keySet()) {
				if (!infectedUsers.contains(i)) {
					userId = i;
				}
			}
		}
		return infectedUserCount;
	}
	public static int limitedInfectionPercentExact (double percent, User user, HashMap<Integer, User> userMap, int[][] coursePairings) {
		int cap = (int) (userMap.size() * (percent / 100));
		int nInfected = limitedInfectionNumberExact(cap, user, userMap, coursePairings);
		return nInfected;
	}
}
