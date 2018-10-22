package roguelike;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class PlayerTest {

	private Point position = new Point(1, 1);

	@Test
	void setupPlayerObject() throws Exception {
		Player testPlayer = new Player(position, "name", 10, 3, 2);
		assertNotNull(testPlayer, "This object should not be null");
	}

	@Test
	void nonEqualObjects() {
		Player testPlayer1 = new Player(position, "name", 10, 3, 2);
		Player testPlayer2 = new Player(position, "name", 10, 3, 2);
		assertNotEquals(testPlayer1, testPlayer2);
	}

	@Test
	void nonEqualStats() {
		Player testPlayer1 = new Player(position, "name", 10, 9, 4);
		Player testPlayer2 = new Player(position, "name", 10, 9, 4);
		assertNotEquals(testPlayer1, testPlayer2);
	}

	@Test
	void listStats() {
		int healthPoints = 10;
		int speed = 5;
		int intelligence = 7;
		Player player = new Player(position, "name", healthPoints, speed, intelligence);
		assertEquals("healthPoints: " + healthPoints + "\nSpeed: " + speed + "\nIntelligence: " + intelligence, player.getStats());

	}
}
