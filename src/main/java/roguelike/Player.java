package roguelike;

import java.awt.Point;
import java.util.Random;

public class Player extends Actor {

	private static final int LEFTWARDS = 0;
	private static final int RIGHTWARDS = 1;
	private static final int UPWARDS = 2;
	private static final int DOWNWARDS = 3;
	private int direction = 2;
	private int fireLength=3;
	
	public int LIFE = 1;

	public Player(Point position, String name, int healthPoints, int speed, int intelligence) {
		super(position, name, healthPoints, speed, intelligence);
	}

	public void moveUp(Level level) {
		double newX = getPosition().getX();
		double newY = getPosition().getY() + 1.0;
		Point newPoint = new Point((int) newX, (int) newY);
	}

	/**
	 * förbrukar 1 intelligence point
	 */
	// antar att player inte finns i listan positions
	public Point moveToRandomPlace() {
		Point p;
		if (intelligence >= 1) {
			Random rand = new Random();
			int x = rand.nextInt(Level.WIDTH + 1);//om olika nivåer har olika storlek?
			int y = rand.nextInt(Level.HEIGHT + 1);//behöver man ta hänsyn till klassen Level?
			p = new Point(x, y);
			boolean illegalPlace = true;
			while (illegalPlace) {
				if (!Level.positions.contains(p) && !p.equals(this.position)) {
					this.position = p;
					illegalPlace = false;
				}
			}
			intelligence--;
			return p;
		} else {
			return this.position;
		}
	}
	/**
	 * förbrukar 2 intelligence point
	 */
	public Point[] throwFire() {
		if (intelligence >= 2) {
			Point[] p = new Point[fireLength];

			if (direction==UPWARDS) {
				for(int i = 0;i<fireLength;i++) {
					p[i]=new Point(position.x,position.y-i-1);
				}		
			}else if(direction==DOWNWARDS) {
				for(int i = 0;i<fireLength;i++) {
					p[i]=new Point(position.x,position.y+i+1);
				}
			}else if(direction==LEFTWARDS) {
				for(int i = 0;i<fireLength;i++) {
					p[i]=new Point(position.x-i-1,position.y);
				}
			}else {
				for(int i = 0;i<fireLength;i++) {
					p[i]=new Point(position.x+i+1,position.y);
				}
			}
			intelligence -= 2;
			return p;
		} else {
			return null;
		}
	}
	public Point moveUp() {
		position.y--;
		direction=UPWARDS;
		return new Point(position.x,position.y);
	}
	public Point moveDown() {
		position.y++;
		direction=DOWNWARDS;
		return new Point(position.x,position.y);
	}

	public Point moveLeft() {
		position.x--;
		direction=LEFTWARDS;
		return new Point(position.x,position.y);
	}

	public Point moveRight() {
		position.x++;
		direction=RIGHTWARDS;
		return new Point(position.x,position.y);
	}

}
