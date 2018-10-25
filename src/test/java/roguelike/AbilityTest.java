package roguelike;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class AbilityTest {
	Level level;
	Player testPlayer1;

	@BeforeEach
	void setUp() {
		testPlayer1 = new Player(level, "name", 10, 9, 4, 10);
		level = new Level("levelName");

		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				level.addPosition(new Point(i, j));
			}
		}

	}

	public void fillLevel() {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				if (i == 1 && j == 1) {
					level.placeEntity(testPlayer1, new Point(i, j));
				} else {
					Actor actor = new Actor(level, "name", 10, 9, 4, 10);
					level.placeEntity(actor, new Point(i, j));
				}
			}
		}
	}

	@Test
	void noOtherPlacesTaken() {

		level.placeEntity(testPlayer1, new Point(1, 1));
		new Teleport("teleport", 2).use(testPlayer1, level);
		assertNotEquals(new Point(1, 1), level.getEntityPlacement(testPlayer1));
		assertNotNull(level.getEntityPlacement(testPlayer1));
	}

	@Test
	void teleportPlayerDecreaseIntelligence() {

		level.placeEntity(testPlayer1, new Point(1, 1));
		new Teleport("teleport", 2).use(testPlayer1, level);
		assertEquals(2, testPlayer1.getIntelligence());
	}

	@Test
	void teleportingPlayerWithNotEnoughIntelligence() {
		Player testPlayer2 = new Player(level, "name", 10, 9, 1, 10);
		level.placeEntity(testPlayer2, new Point(1, 1));
		new Teleport("teleport", 2).use(testPlayer2, level);
		assertEquals(new Point(1, 1), level.getEntityPlacement(testPlayer2));
		assertNotNull(level.getEntityPlacement(testPlayer2));
	}

	
	@Test
	void allPlacesTakenAndIntelligenceIsEnough() {
		fillLevel();
		int beforeIntelligence = testPlayer1.getIntelligence();
		Assertions.assertTimeout(Duration.ofMillis(2), () ->{
		new Teleport("teleport", 2).use(testPlayer1, level);

		});
		assertNotEquals(beforeIntelligence, testPlayer1.getIntelligence());
		assertEquals(new Point(1, 1), level.getEntityPlacement(testPlayer1));
		

	}

	@Test
	void oneAvailablePlace() {
		fillLevel();
		level.emptyPosition(new Point(2, 2));

		new Teleport("teleport", 2).use(testPlayer1, level);

		assertEquals(new Point(2, 2), level.getEntityPlacement(testPlayer1));
	}

	@Test
	void someAvailablePositions() {
		Actor actor1 = new Actor(level, "name", 10, 9, 1, 10);
		Actor actor2 = new Actor(level, "name", 10, 9, 1, 10);

		level.placeEntity(testPlayer1, new Point(1, 1));
		level.placeEntity(actor1, new Point(2, 2));
		level.placeEntity(actor2, new Point(3, 3));

		new Teleport("teleport", 2).use(testPlayer1, level);

		assertNotEquals(new Point(1, 1), level.getEntityPlacement(testPlayer1));
		assertNotNull(level.getEntityPlacement(testPlayer1));

	}

	@Test
	void illegalRequiredIntelligence() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Teleport("name", 100);
		});

	}


}
