package roguelike;

import java.awt.Point;

public class ListStats extends Actor {

	public ListStats(Point position, String name, int healthPoints, int speed, int intelligence) {
		super(position, name, healthPoints, speed, intelligence);
	}

	public static ListStats getStats() {
		return null;
	}

}
