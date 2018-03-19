package YatzyGame;

import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {

	private String name;
	int[] score;

	public Player(String name) {
		this.name = name;
		score = new int[16];
		for (int i = 0; i < score.length; i++) {
			score[i] = -1;
		}
	}

	public String getName() {
		return name;
	}

	public void rollTheDie(Die[] dice) {

		for (int i = 0; i < dice.length; i++) {
			if (dice[i] != null) {
				continue;
			}
			Die temp = new Die();
			dice[i] = temp;
		}
	}

	public int getCurrentScore() {
		int sum = 0;
		for (int i = 0; i < this.score.length; i++) {
			if (this.score[i] >= 0) {
				sum += this.score[i];
			}
		}
		return sum;
	}

	public void checkBonus() {
		if (this.score[15] == -1) {
			int sum = 0;
			for (int i = 0; i < 6; i++) {
				sum += this.score[i];
			}
			if (sum >= 63) {
				this.score[15] = 50;
				System.out.println("\nCongratulations, you've received a bonus!");
			}
		}
	}

	public void printScorecard() {
		String[] categories = new String[] { "Aces", "Twos", "Threes", "Fours", "Fives", "Sixes", "One pair",
				"Two pairs", "Three of a kind", "Four of a kind", "Full house", "Small straight", "Large straight",
				"Yahtzee", "Chance", "Bonus" };
		for (int i = 0; i < score.length; i++) {
			System.out.println("Category " + (i + 1) + ": Points: " + this.score[i] + " / " + categories[i]);
		}
	}

	public int compareTo(Player p) {
		if (this.getCurrentScore() < p.getCurrentScore()) {
			return -1;
		} else if (this.getCurrentScore() == p.getCurrentScore()) {
			return 0;
		} else {
			return 1;
		}
	}

}
