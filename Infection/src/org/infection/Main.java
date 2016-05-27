package org.infection;

import java.util.ArrayList;

public class Main {

	/**
	 * In Main, I instantiate objects from the Creator and Infection classes.
	 * The Creator object allows us to pull a roster of all of our objects from
	 * Creator, as well as generate a random user from this roster who will
	 * become the starting point of our infections.
	 * 
	 * You can un-comment the "totalInfection" and "limitedInfectionPercent"
	 * calls to see what happens. The '2' at the end of every call is a label
	 * for the new website version that we are infecting users with (remember,
	 * the current default is 1). In "limitedInfection", the first int refers to
	 * how many users we want to infect. In "limitedInfectionPercent", the first
	 * int refers to the percentage of our users that we want to infect.
	 */

	public static void main(String[] args) {
		Creator creator = new Creator();
		Infection infection = new Infection();
		ArrayList<User> userRoster = creator.getUserRoster();
		User user = creator.getRandomUser();

		System.out.println("Patient 0: " + user.getName());
		// int infected = infection.totalInfection(user, 2);
		int infected = infection.limitedInfection(user, 4, userRoster, 2);
		// int infected = infection.limitedInfectionPercent(user, 25, userRoster, 2);

		System.out.println("We have infected " + infected + " people.");
	}
}
