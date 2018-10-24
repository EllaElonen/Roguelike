package roguelike;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class AbilityTest {
	Level level;

	@BeforeEach
	void setUp() {
		level = new Level("levelName");

		for(int i=1;i<10;i++) {
			for(int j=1;j<10;j++) {
				level.addPosition(new Point(i,j));
			}
		}
		
	}

	@Test
	void teleportPlayer() {
		Player testPlayer1 = new Player(level, "name", 10, 9, 4, 10);
		level.placeEntity(testPlayer1, new Point(1,1));
		new Teleport("teleport",2).use(testPlayer1, level);		
		assertNotEquals(new Point(1,1),level.getEntityPlacement(testPlayer1));
		assertNotNull(level.getEntityPlacement(testPlayer1)	);
}
	@Test
	void teleportPlayerDecreaseIntelligence() {
		Player testPlayer1 = new Player(level, "name", 10, 9, 4, 10);
		level.placeEntity(testPlayer1, new Point(1,1));
		new Teleport("teleport",2).use(testPlayer1, level);	
		assertEquals(2,testPlayer1.getIntelligence());
	}
	@Test
	void failTeleportingPlayer() {
		Player testPlayer1 = new Player(level, "name", 10, 9, 1, 10);
		level.placeEntity(testPlayer1, new Point(1,1));
		new Teleport("teleport",2).use(testPlayer1, level);		
		assertEquals(new Point(1,1),level.getEntityPlacement(testPlayer1));
		assertNotNull(level.getEntityPlacement(testPlayer1)	);
	}
	
	
}
