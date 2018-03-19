package YatzyGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Launch {

	public static void main(String[] args) {

		Game game = null;

		File f = new File("game.ser");
		if (f.exists() && !f.isDirectory()) {
			System.out.println("Do you want to load a previous game (yes/no)?");
			Scanner scanner = new Scanner(System.in);
			String answer = scanner.nextLine();

			if (answer.equals("yes")) {
				try {
					FileInputStream inFile = new FileInputStream("game.ser");
					ObjectInputStream inStream = new ObjectInputStream(inFile);
					game = (Game) inStream.readObject();
					inStream.close();
					inFile.close();

				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.println("Game class was not found");
					e.printStackTrace();
				}
			} else {
				game = new Game();
				game.createPlayerList();
			}
		} else {
			game = new Game();
			game.createPlayerList();
		}
		game.startGame();
	}

}
