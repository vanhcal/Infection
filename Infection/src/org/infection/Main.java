package org.infection;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Creator creator = new Creator();
		ArrayList<User> userRoster = creator.getUserRoster();
		Infection infection = new Infection();
		User user = creator.getRandomUser();
		
		System.out.println("Patient 0: " + user.getName());
		int infected = infection.limitedInfection(user, 1, userRoster, 2);

		System.out.println("We have infected " + infected + " people.");
	}
}
