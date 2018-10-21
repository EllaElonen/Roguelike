package roguelike;

import java.awt.Point;

public class Monster extends Actor {

    public static Point position;

    public Monster(Point position, String name, int healthPoints, int speed, int intelligence) {
		super(position, name, healthPoints, speed, intelligence);
	}
	public Point getPosition(){
        return position;
    }

    public static void setPosition(Point position) {
        Monster.position = position;
    }
}
