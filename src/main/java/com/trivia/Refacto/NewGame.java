package com.trivia.Refacto;

import java.util.ArrayList;
import java.util.List;

public class NewGame {
	public List<Player> players = new ArrayList<>();
	public List<CategorieQuestion> questions = new ArrayList<>();
	public Integer currentPlayerIndex = 0;
	public Integer indexCategorie = 0;

	public NewGame() {
		questions.add(new CategorieQuestion("Pop", 50));
		questions.add(new CategorieQuestion("Science", 50));
		questions.add(new CategorieQuestion("Sports", 50));
		questions.add(new CategorieQuestion("Rock", 50));
	}

	public void addPlayer(String playerName) {
		Player player = new Player(playerName, this.players.size() + 1, 0, false, false);
		players.add(player);

		System.out.println(playerName + " was added");
		System.out.println("They are " + players.size() + "player");
	}

	public void playATurn(int diceRoll) {
		Player player = this.players.get(currentPlayerIndex);
		System.out.println(player.getName() + " is the current player");
		System.out.println("They have rolled a " + diceRoll);

		if (player.isInPenaltyBox()) {
			if (diceRoll % 2 != 0) {
				player.setIsGettingOutOfPenaltyBox(true);
				System.out.println(player.getName() + " is getting out of the penalty box");
			} else {
				System.out.println(player.getName() + " is not getting out of the penalty box");
				player.setIsGettingOutOfPenaltyBox(false);
				return;
			}
		}
		player.moveForward(diceRoll);
		System.out.println("The category is " + currentCategory());
		askQuestion();

	}

	private void askQuestion() {
		for (CategorieQuestion question : questions) {
			if (currentCategory().equals(question.categorie)) {
				System.out.println("Removing question from category : " + question.categorie);
				question.removeQuestion();
			}
		}
	}

	private String currentCategory() {
		Integer place = this.players.get(this.currentPlayerIndex).getPlace();
		if (place == 0 || place == 4 || place == 8)
			return "Pop";
		if (place == 1 || place == 5 || place == 9)
			return "Science";
		if (place == 2 || place == 6 || place == 10)
			return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		Player player = this.players.get(currentPlayerIndex);
		if (player.isInPenaltyBox()) {
			if (player.getIsGettingOutOfPenaltyBox()) {
				return addCoinToPlayer(player);
			} else {
				moveToNextPlayer();
				return false;
			}

		} else {
			return addCoinToPlayer(player);
		}
	}

	private boolean addCoinToPlayer(Player player) {
		System.out.println("Answer was correct!!!!");
		player.addCoin();

		moveToNextPlayer();

		return player.didPlayerWin();
	}

	private void moveToNextPlayer() {
		this.currentPlayerIndex++;
		if (currentPlayerIndex == this.players.size())
			currentPlayerIndex = 0;
	}

	public boolean wrongAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(this.players.get(currentPlayerIndex).getName() + " was sent to the penalty box");
		this.players.get(currentPlayerIndex).setInPenaltyBox(true);

		moveToNextPlayer();
		return false;
	}

}