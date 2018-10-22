package roguelike;

import java.awt.Point;

public class Player extends Actor {

	public Player(Point position, String name, int healthPoints, int speed, int intelligence) {
		super(position, name, healthPoints, speed, intelligence);
	}

	public void moveUp(Level level) {
		double newX = getPosition().getX();
		double newY = getPosition().getY() + 1.0;
		Point newPoint = new Point((int) newX, (int) newY);
	}

	public String getStats() {
		return "healthPoints: " + getHealthPoints() + "\nSpeed: " + getSpeed() + "\nIntelligence: " + getIntelligence();
	}


}
