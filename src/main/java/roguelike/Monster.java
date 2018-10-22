package roguelike;

import java.awt.Point;

public class Monster extends Actor {

    public static Point position;

    public Monster(Point position, String name, int healthPoints, int speed, int intelligence, int strength) {
		super(position, name, healthPoints, speed, intelligence, strength);
	}
	public Point getPosition(){
        return position;
    }

   /* public int getStrenght(){
        return strength;
    }*/

    public static void setPosition(Point position) {
        Monster.position = position;
    }
}
