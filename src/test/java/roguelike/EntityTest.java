package roguelike;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {
	Entity actor1;
	String id;
	Level level;
	
	@BeforeEach
	public void setUp() {
		id = "123";
		level = new Level("Crypt");
		actor1 = new Actor(level, "name", 10, 3, 2, id, 2);
	}
	
	@Test
	public void equalsActorWithSameID() {
		Entity actor2 = new Actor(level, "name2", 2, 23, 3, id, 4);
		assertEquals(actor1, actor2);
	}
	
	@Test
	public void equalsWithNonActor() {
		assertNotEquals(actor1, "Test");
	}
}
