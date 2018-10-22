package roguelike;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Point;

import org.junit.Test;

public class ListStatsTest {

	private Point position = new Point(1,1);

	@Test
	public void StatsNotNull() {
		ListStats playerStats = new ListStats(position, "name", 10, 5, 8);
		assertNotNull(playerStats, "this object should not be null");
	}

	@Test
	public void ListStats() {
		ListStats playerStats2 = ListStats.getStats();
		assertTrue(ListStats.getStats() == playerStats2);
		
	}

}
