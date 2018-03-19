
package YatzyGame;

import java.util.Arrays;

public class Category {

	public static int sameNumbers(Die[] hand, int numbers) {
		int sum = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].getValue() == numbers) {
				sum = sum + numbers;
			}
		}
		return sum;
	}
        
	public static int onePair(Die[] hand) {
		int sum = 0;
		int[] temp = new int[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = hand[i].getValue();
		}
		Arrays.sort(temp);

		for (int j = 1; j < 7; j++) {
			int counter = 0;
			int dieValue = 0;
			for (int die : temp) {
				if (j == die) {
					counter++;
					dieValue = die;
				}
			}
			if (counter >= 2) {
				sum = dieValue * 2;
			}
		}
		return sum;
	}
        
	public static int twoPairs(Die[] hand) {
		int sum = 0;
		int[] temp = new int[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = hand[i].getValue();
		}
		Arrays.sort(temp);
                 
                if (temp[0] == temp[1] && temp[2] == temp[3]) {
			sum = temp[0] * 2 + temp[2] * 2;
		}
		if (temp[0] == temp[1] && temp[3] == temp[4]) {
			sum = temp[0] * 2 + temp[3] * 2;
		}
		if (temp[1] == temp[2] && temp[3] == temp[4]) {
			sum = temp[1] * 2 + temp[3] * 2;
		}                
                return sum;
	}
        
	public static int threeOfaKind(Die[] hand) {
		int sum = 0;
		int[] temp = new int[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = hand[i].getValue();
		}
		Arrays.sort(temp); 

		if (temp[0] == temp[1] && temp[1] == temp[2]) {
			sum = temp[0] * 3;
		}
		if (temp[1] == temp[2] && temp[2] == temp[3]) {
			sum = temp[1] * 3;
		}
		if (temp[2] == temp[3] && temp[3] == temp[4]) {
			sum = temp[2] * 3;
		}
		return sum;
	}
        
	public static int fourOfaKind(Die[] hand) {
		int sum = 0;
		int[] temp = new int[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = hand[i].getValue();
		}
		Arrays.sort(temp); 

		if (temp[0] == temp[1] && temp[1] == temp[2] && temp[2] == temp[3]) {
			sum = temp[0] * 4;
		}
		if (temp[1] == temp[2] && temp[2] == temp[3] && temp[3] == temp[4]) {
			sum = temp[1] * 4;
		}
		return sum;
	}
        
	public static int fullHouse(Die[] hand) {
		int sum = 0;
		int[] temp = new int[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = hand[i].getValue();
		}
		Arrays.sort(temp); 

		if (temp[0] == temp[1] && temp[2] == temp[3] && temp[3] == temp[4] && temp[1] != temp[2]) {
			sum = temp[0] * 2 + temp[2] * 3;
		}
		if (temp[0] == temp[1] && temp[1] == temp[2] && temp[3] == temp[4] && temp[2] != temp[3]) {
			sum = temp[0] * 3 + temp[3] * 2;
		}
		return sum;
	}
        
	public static int smallStraight(Die[] hand) {
		int sum = 0;
		int[] temp = new int[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = hand[i].getValue();
		}
		Arrays.sort(temp);

		for (int j = 1; j < temp.length + 1; j++) {
			if (temp[j - 1] == j) {
				sum = sum + j;
			}
		}
		if (sum == 15) {
			return 15;
		} else {
			return 0;
		}
	}
        
	public static int largeStraight(Die[] hand) { // c == 13 VALMIS
		int sum = 0;
		int[] temp = new int[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = hand[i].getValue();
		}
		Arrays.sort(temp);

		for (int j = 2; j < temp.length + 2; j++) {
			if (temp[j - 2] == j) {
				sum = sum + j;
			}
		}
		if (sum == 20) {
			return 20;
		} else {
			return 0;
		}
	}
        
	public static int Yatzy(Die[] hand) {
		int value = hand[0].getValue();
		for (Die d : hand) {
			if (value != d.getValue()) {
				return 0;
			}
		}
		return 50;
	}
        
	public static int chance(Die[] hand) {
		int sum = 0;
		for (int i = 0; i < hand.length; i++) {
			sum = sum + hand[i].getValue();
		}
		return sum;
	}

}

