package roguelike;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {
	String levelName = "Cellar 1-1";
	Level level;

	@BeforeEach
	void setUp() throws Exception {
		level = new Level(levelName);
	}

	@Test
	void moveEntityWithoutPosition() {
		Actor actor = new Actor(level, "name", 10, 55, 2, 10);
		level.moveActor(actor, Direction.UP);
		assertFalse(level.entityExists(actor));
	}

	@Test
	void getNameOfLevel() {
		assertEquals(level.getName(), levelName);
	}

	@Test
	void setPositionsOnNewlyCreatedLevel() {
		level.addPosition(new Point(1, 1));

		assertEquals(level.positionExists(new Point(1, 1)), true);
	}

	@Test
	void placeEntityOnLocation() {
		Point point = new Point(2, 2);
		Actor actor = new Actor(level, "name", 10, 3, 2, 5);

		level.addPosition(point);
		level.placeEntity(actor, point);

		assertEquals(level.getEntityPlacement(actor), point);
	}

	@Test
	void placeEntityOnNonExistantLocation() {
		Actor actor = new Actor(level, "name", 10, 3, 2, 5);

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			level.placeEntity(actor, new Point(2, 2));
		});
		assertEquals(exception.getMessage(), "Level does not have that point.");
	}

	@Test
	void placeEntityOnTakenLocation() {
		Actor actor1 = new Actor(level, "name", 10, 3, 2, 5);
		Actor actor2 = new Actor(level, "name", 10, 3, 2, 5);
		Point point = new Point(1, 1);

		level.addPosition(point);
		level.placeEntity(actor1, point);

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			level.placeEntity(actor2, point);
		});
		assertEquals(exception.getMessage(), "Point is taken.");
	}

	@Test
	void getEntityFromPosition() {
		Actor actor = new Actor(level, "name", 10, 3, 2, 5);
		Point point = new Point(1, 1);

		level.addPosition(point);
		level.placeEntity(actor, point);

		assertEquals(level.getEntityInLocation(point), actor);
	}

	@Test
	void addTwoEqualPositions() {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(1, 1);

		level.addPosition(p1);
		level.addPosition(p2);

		assertEquals(level.getPositions().size(), 1);
	}

	@Test
	void moveEntityUp() {
		Actor actor = new Actor(level, "name", 10, 3, 2, 5);

		level.addPosition(new Point(1, 1));
		level.addPosition(new Point(1, 2));
		level.placeEntity(actor, new Point(1, 1));
		level.moveActor(actor, Direction.UP);

		assertEquals(level.getEntityPlacement(actor), new Point(1, 2));
	}
	

	@Test
	void moveEntityToNonExistingPosition() {
		Actor actor = new Actor(level, "name", 10, 3, 2, 5);

		level.addPosition(new Point(1, 1));
		level.addPosition(new Point(1, 2));
		level.placeEntity(actor, new Point(1, 1));
		level.moveActor(actor, Direction.DOWN);
		assertEquals(new Point(1, 1), level.getEntityPlacement(actor));
	}

	@Test
	void moveEntityToTakenLocation() {
		Actor actor1 = new Actor(level, "name", 10, 3, 2, 5);
		Actor actor2 = new Actor(level, "name", 10, 3, 2, 5);

		level.addPosition(new Point(1, 1));
		level.addPosition(new Point(2, 1));
		level.placeEntity(actor1, new Point(1, 1));
		level.placeEntity(actor2, new Point(2, 1));
		level.moveActor(actor1, Direction.RIGHT);

		assertEquals(level.getEntityPlacement(actor1), new Point(1, 1));
	}

	@Test
	void walkOverItem() {
		Player player = new Player(level, "name", 10, 3, 2, 5);
		Item item = new Item(level, "Pilbåge", EquipmentSlot.WEAPON, 0, 0);

		level.addPosition(new Point(1, 1));
		level.addPosition(new Point(2, 1));
		level.placeEntity(player, new Point(1, 1));
		level.placeEntity(item, new Point(2, 1));

		level.movePlayer(player, Direction.RIGHT);
		assertEquals(new Point(1, 1), level.getEntityPlacement(player));
		assertEquals(null, level.getEntityInLocation(new Point(2, 1)));
	}

	@Test
	void walkOverActor() {
		Player player = new Player(level, "name", 10, 3, 2, 5);
		Actor opponent = new Actor(level, "name", 10, 3, 2, 10);

		level.addPosition(new Point(1, 1));
		level.addPosition(new Point(2, 1));
		level.placeEntity(player, new Point(1, 1));
		level.placeEntity(opponent, new Point(2, 1));

		level.movePlayer(player, Direction.RIGHT);
		assertFalse(level.entityExists(player));
	}
	
	@Test
	void WalkIntoWall() {
		Player player = new Player(level, "name", 10, 3, 2, 5);

		level.addPosition(new Point(1, 1));
		level.addPosition(new Point(2, 1));
		level.placeEntity(player, new Point(1, 1));
		
		level.movePlayer(player, Direction.LEFT);
		assertEquals(new Point(1, 1), level.getEntityPlacement(player));
	}
}