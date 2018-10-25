package roguelike;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.Test;

public class ActorTest {
	Level level= new Level("levelName");
	//Point point = new Point(1,1);
	
	@Test
	public void negativeHP() {
		assertThrows (IllegalArgumentException.class, () ->{
			new Actor(level, "name", -10, 5, 2, 10);
		});
	}
	
	@Test
	public void negativeStrength() {
		assertThrows (IllegalArgumentException.class, () ->{
			new Actor(level, "name", 10, 5, 2, -10);
		});
	}
	
	@Test
	public void negativeSpeed() {
		assertThrows (IllegalArgumentException.class, () ->{
			new Actor(level, "name", 10, -5, 2, 10);
		});	
	}
	
	@Test
	public void negativeIntelligence() {
		assertThrows (IllegalArgumentException.class, () ->{
			new Actor(level, "name", 10, 5, -2, 10);
		});	
	}

	
	@Test
	public void listStats() {
		int healthPoints = 10;
		int speed = 5;
		int intelligence = 7;
		int strength = 5;
		Player player = new Player(level, "name", healthPoints, speed, intelligence, strength);

		assertEquals(player.getStats(),"healthPoints: " + healthPoints + "\nSpeed: " + speed + "\nIntelligence: " + intelligence + "\nStrength: " + strength);

    }
}
