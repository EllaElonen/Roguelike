package roguelike;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeEach;

public class LevelTest {
	
	Level level;
	
	@BeforeEach
	void setUp() throws Exception {
		level = new Level();
	}
	
	@Test
	void setPositionsOnNewlyCreatedLevel() {
		level.addPosition(new Point(1,1));
		
		assertEquals(level.positionExists(new Point(1, 1)), true);
	}
	
	@Test
	void placeEntityOnLocation() {
		Point point = new Point(2,2);
		Entity entity = new Entity(point);
		
		level.addPosition(point);
		level.placeEntity(entity, point);
		
		assertEquals(level.getEntityPlacement(entity), point);
	}
	
	@Test
	void placeEntityOnNonExistantLocation() {
		Entity entity = new Entity(new Point(2,2));
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			level.placeEntity(entity, new Point(2,2));
		});
		assertEquals(exception.getMessage(), "Level does not have that point.");
	}
	
	@Test
	void placeEntityOnTakenLocation() {
		Entity entity1 = new Entity(new Point(1, 1));
		Entity entity2 = new Entity(new Point(1, 1));
		Point point = new Point(1,1);
		
		level.addPosition(point);
		level.placeEntity(entity1, point);
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			level.placeEntity(entity2, point);
		});
		assertEquals(exception.getMessage(), "Point is taken.");
	}
	
	@Test
	void getEntityFromPosition() {
		Entity entity = new Entity(new Point(1, 1));
		Point point = new Point(1,1);
		
		level.addPosition(point);
		level.placeEntity(entity, point);
		
		assertEquals(level.getEntityInLocation(point), entity);
	}
}