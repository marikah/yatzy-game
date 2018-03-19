package YatzyGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class YatzyTests {

	@Test
	public void FiveSimilarGetsYatzy() {

		Die[] hand = new Die[5];

		for (int i = 0; i < 5; ++i) {
			hand[i] = new Die(1);
		}

		int result = Category.Yatzy(hand);
		assertEquals(50, result);

	}

	@Test
	public void YatzyNotExpected() {

		Die[] hand = new Die[5];

		for (int i = 0; i < 4; ++i) {
			hand[i] = new Die(1);
		}
		hand[4] = new Die(2);

		int result = Category.Yatzy(hand);
		assertNotEquals(50, result);

	}

}
