package YatzyGame;

import java.util.Random;

public class Die implements Comparable<Die> {
	private Random rnd;
	private int value;

	public Die() {
		rnd = new Random();
		value = rnd.nextInt(6) + 1;
	}

	public Die(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public int compareTo(Die d) {
		if (this.value < d.getValue()) {
			return -1;
		} else if (this.value == d.getValue()) {
			return 0;
		} else {
			return 1;
		}
	}
}
