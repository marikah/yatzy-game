package YatzyGame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Game implements Serializable {
	private ArrayList<Player> players;
	private final int rounds = 15;
	private int current = 0;
	private Player winner;

	public Game() {
	}

	public void createPlayerList() {
		Scanner scan = new Scanner(System.in);
		players = new ArrayList<Player>();
		boolean add = true;
		String answer = "";
		Player p;
		System.out.println("\nLet's start the game by adding some players.");
		while (add) {
			System.out.println("\nThe name of the next player:");
			answer = scan.nextLine();
			p = new Player(answer);
			players.add(p);
			System.out.println("\nAdd more players? (yes/no)");
			answer = scan.nextLine();
			if (!answer.equals("yes")) {
				add = false;
			}
		}
	}

	public void printDice(Die[] dice) {
		for (int i = 0; i < dice.length; i++) {
			System.out.println("Die: " + (i + 1) + ", Value: " + dice[i].getValue());
		}
	}

	public void saveGame() {
		try {
			FileOutputStream outFile = new FileOutputStream("game.ser");
			ObjectOutputStream outStream = new ObjectOutputStream(outFile);
			outStream.writeObject(this);
			outStream.close();
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void checkWinner() {
		winner = this.players.get(0);
		for (int i = 0; i < this.players.size(); i++) {
			if (this.players.get(i).getCurrentScore() > winner.getCurrentScore()) {
				winner = this.players.get(i);
			}
		}
	}

	private boolean checkNumericalInput(String input, int lowerLimit, int upperLimit, boolean split) {
		String[] bounds = null;

		if (split) {
			bounds = input.split("");
		} else {
			bounds = new String[1];
			bounds[0] = input;
		}
		for (String s : bounds) {
			try {
				int value = Integer.parseInt(s);
				if (value < lowerLimit || value > upperLimit) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	public void startGame() {
		Scanner s = new Scanner(System.in);
		while (this.current < this.rounds) {
			for (Player p : players) {
				System.out.println("\n" + p.getName());
				System.out.println("\nScoreboard:");
				p.printScorecard();
				int round = 1;

				Die[] hand = new Die[5];
				p.rollTheDie(hand);
				System.out.println("\n" + p.getName() + "'s dice:");
				printDice(hand);

				do {
					System.out.println(
							"\nSpecify which dice to roll again by number or press enter to continue to choose category:");

					String dice = s.nextLine();

					if (dice.equals("")) {
						break;
					}

					if (this.checkNumericalInput(dice, 1, 5, true)) {
						String[] parts = dice.split("");
						for (int j = 0; j < parts.length; j++) {
							hand[Integer.parseInt(parts[j]) - 1] = new Die();
						}
						System.out.println("\n" + p.getName() + "'s dice after roll:");
						printDice(hand);

						round++;
					} else {
						System.out.println("\nInput was invalid, try again.");
					}
				} while (round < 3);

				boolean ok = false;
				int categoryNumber = 0;
				do {
					System.out.println("\nChoose a category by number:");
					String category = s.nextLine();

					if (this.checkNumericalInput(category, 1, 15, false)) {
						categoryNumber = Integer.parseInt(category);
						if (p.score[categoryNumber - 1] == -1) {
							ok = true;
						} else {
							System.out.println("\nThe category has been used, choose another one.");
						}
					}
				} while (!ok);

				if (categoryNumber >= 1 && categoryNumber <= 6) {
					p.score[categoryNumber - 1] = Category.sameNumbers(hand, categoryNumber);
				} else if (categoryNumber == 7) {
					p.score[categoryNumber - 1] = Category.onePair(hand);
				} else if (categoryNumber == 8) {
					p.score[categoryNumber - 1] = Category.twoPairs(hand);
				} else if (categoryNumber == 9) {
					p.score[categoryNumber - 1] = Category.threeOfaKind(hand);
				} else if (categoryNumber == 10) {
					p.score[categoryNumber - 1] = Category.fourOfaKind(hand);
				} else if (categoryNumber == 11) {
					p.score[categoryNumber - 1] = Category.fullHouse(hand);
				} else if (categoryNumber == 12) {
					p.score[categoryNumber - 1] = Category.smallStraight(hand);
				} else if (categoryNumber == 13) {
					p.score[categoryNumber - 1] = Category.largeStraight(hand);
				} else if (categoryNumber == 14) {
					p.score[categoryNumber - 1] = Category.Yatzy(hand);
				} else if (categoryNumber == 15) {
					p.score[categoryNumber - 1] = Category.chance(hand);
				}

				p.checkBonus();

				System.out.println("\n" + p.getName() + "'s score after round " + (current + 1) + " is "
						+ p.getCurrentScore() + ".");

			}
			this.current++;

			this.checkWinner();

			System.out.println("\n********************");
			System.out.println("\nRound " + current + " played.");

			if (current == 15) {
				System.out.println("\nThe winner is " + winner.getName() + " with score " + winner.getCurrentScore()
						+ ". The scoreboard looks like this:");
				System.out.println();
				winner.printScorecard();
				break;
			}

			System.out.println("\nThe leading player is " + winner.getName());

			System.out.println("\n********************");

			System.out.println("\nSave and exit (yes)?");
			System.out.println();
			String answer = s.nextLine();

			if (answer.equals("yes")) {
				this.saveGame();
				s.close();
				break;
			}
		}
	}
}