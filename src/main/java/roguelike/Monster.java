package roguelike;

import java.awt.Point;

public class Monster extends Actor {

	public Monster(Point position, String name, int healthPoints, int speed, int intelligence) {
		super(position, name, healthPoints, speed, intelligence);
	}
}
