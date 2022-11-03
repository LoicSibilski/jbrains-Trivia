package com.trivia.Refacto;

import java.util.Random;

public class NewGameRunner {
    private static boolean winner;

	public static void main(String[] args) {
		NewGame newGame = new NewGame();

		newGame.addPlayer("Oui");
		newGame.addPlayer("Ouaf");
		newGame.addPlayer("Woof");

		Random rand = new Random();

		do {
            System.out.println("________________________");
			newGame.playATurn(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				winner = newGame.wrongAnswer();
			} else {
				winner = newGame.wasCorrectlyAnswered();
			}

		} while (!winner);

	}
}
