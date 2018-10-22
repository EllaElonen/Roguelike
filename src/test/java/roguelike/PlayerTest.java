package roguelike;

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
	void failMovingToRandomPlace() {
		Player testPlayer3 = new Player(position, "name3", 10, 3, 0);
		// testPlayer3.moveToRandomPlace();
		assertEquals(testPlayer3.moveToRandomPlace(), position);
	}

	@Test
	void succeedInMovingToRandomPlace() {
		Player testPlayer4 = new Player(position, "name3", 10, 3, 1);
		/**
		 * ordning påverkar resultat
		 */
		// failed
		// assertNotEquals(testPlayer4.moveToRandomPlace(), testPlayer4.position);
		assertNotEquals(testPlayer4.position, testPlayer4.moveToRandomPlace());
	}
	@Test
	void failThrowingFire() {
		Player testPlayer5 = new Player(position, "name3", 10, 3, 1);
		assertEquals(testPlayer5.throwFire(), null);
	}
	@Test
	void succeedInThrowingFireRightwards() {
		Player testPlayer6 = new Player(position, "name3", 10, 3, 3);
		testPlayer6.moveRight();
		//arrayen måste vara exakt samma
		//Point[] points = { new Point(5, 1), new Point(3, 1), new Point(4, 1) };
		Point[] points = { new Point(3, 1), new Point(4, 1), new Point(5, 1) };
		assertArrayEquals(points, testPlayer6.throwFire());
	}
	@Test
	void succeedInThrowingFireUpwards(){	
		Player testPlayer7 = new Player(new Point(10,10), "name3", 10, 3, 3);	
		Point[] points = { new Point(10, 9), new Point(10, 8), new Point(10, 7) };
		assertArrayEquals(points, testPlayer7.throwFire());
	}
}
