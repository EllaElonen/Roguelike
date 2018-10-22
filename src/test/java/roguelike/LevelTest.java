package roguelike;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeEach;

public class LevelTest {
	String levelName = "Cellar 1-1";
	Level level;
	
	@BeforeEach
	void setUp() throws Exception {
		level = new Level(levelName);
	}
	
	@Test
	void getNameOfLevel() {
		assertEquals(level.getName(), levelName);
	}
	@Test
	void setPositionsOnNewlyCreatedLevel() {
		level.addPosition(new Point(1,1));
		
		assertEquals(level.positionExists(new Point(1, 1)), true);
	}
	
	@Test
	void placeEntityOnLocation() {
		Point point = new Point(2,2);
		Actor actor = new Actor(point, "name", 10, 3, 2,4);
		
		level.addPosition(point);
		level.placeEntity(actor, point);
		
		assertEquals(level.getEntityPlacement(actor), point);
	}
	
	@Test
	void placeEntityOnNonExistantLocation() {
		Actor actor = new Actor(new Point(1, 1), "name", 10, 3, 2,4);
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			level.placeEntity(actor, new Point(2,2));
		});
		assertEquals(exception.getMessage(), "Level does not have that point.");
	}
	
	@Test
	void placeEntityOnTakenLocation() {
		Actor actor1 = new Actor(new Point(1, 1), "name", 10, 3, 2,4);
		Actor actor2 = new Actor(new Point(1, 1), "name", 10, 3, 2,4);
		Point point = new Point(1,1);		
		level.addPosition(point);
		level.placeEntity(actor1, point);
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			level.placeEntity(actor2, point);
		});
		assertEquals(exception.getMessage(), "Point is taken.");
	}
	@Test
	void getEntityFromPosition() {
		Actor actor = new Actor(new Point(1, 1), "name", 10, 3, 2,4);
		Point point = new Point(1,1);
		
		level.addPosition(point);
		level.placeEntity(actor, point);
		
		assertEquals(level.getEntityInLocation(point), actor);
	}
	
//	@Test
//	void addTwoEqualPositions() {
//		Point p1 = new Point(1,1);
//		Point p2 = new Point(1,1);
//		
//		level.addPosition(p1);
//		level.addPosition(p2);
//		
//		assertEquals(level.getPositions().size(), 1);
//	}
	
	@Test
	void moveEntityUp() {
		Actor actor = new Actor(new Point(1, 1), "name", 10, 3, 2,4);
		
		level.addPosition(new Point(1, 1));
		level.addPosition(new Point(1, 2));
		level.placeEntity(actor, new Point(1, 1));
		level.moveEntity(actor, Direction.UP);
		
		assertEquals(level.getEntityPlacement(actor), new Point(1, 2));
	}
	
	@Test 
	void moveEntityToNonExistingPosition() {
		Actor actor = new Actor(new Point(1, 1), "name", 10, 3, 2,4);
		
		level.addPosition(new Point(1, 1));
		level.addPosition(new Point(1, 2));
		level.placeEntity(actor, new Point(1, 1));
		level.moveEntity(actor, Direction.DOWN);
		assertEquals(new Point(1, 1), level.getEntityPlacement(actor));
	}
	
	
}