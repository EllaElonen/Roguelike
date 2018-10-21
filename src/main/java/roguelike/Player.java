package roguelike;

import java.awt.Point;

public class Player extends Actor {

    private int extraLives;
    private int magicPoints;

	public Player(Point position, String name, int healthPoints, int speed, int intelligence) {
		super(position, name, healthPoints, speed, intelligence);
	}

	public void moveUp(Level level) {
		double newX = getPosition().getX();
		double newY = getPosition().getY() + 1.0;
		Point newPoint = new Point((int) newX, (int) newY);
		setPosition(newPoint);
	}

    public void moveDown(Level level) {
        double newX = getPosition().getX();
        double newY = getPosition().getY() - 1.0;
        Point newPoint = new Point((int) newX, (int) newY);
        setPosition(newPoint);
    }

    public void moveLeft(Level level) {
        double newX = getPosition().getX() - 1.0;
        double newY = getPosition().getY();
        Point newPoint = new Point((int) newX, (int) newY);
        setPosition(newPoint);
    }

    public void moveRight(Level level) {
        double newX = getPosition().getX()+ 1.0;
        double newY = getPosition().getY();
        Point newPoint = new Point((int) newX, (int) newY);
        setPosition(newPoint);
    }

    public void setPosition(Point position){
	    this.position = position;
    }

    public int getExtraLives(){
	    return extraLives;
    }

    public void addExtraLives(int amount){
	    extraLives = extraLives + amount;
    }

    public void attack(Monster monster){
        position = monster.getPosition();
            if (monster.getHealthPoints() > healthPoints){
                healthPoints = healthPoints - 6;
            }
            else if(monster.getHealthPoints() == healthPoints){
                healthPoints = healthPoints - 4;
            }
            else{
                healthPoints = healthPoints -2;
            }
            //TODO If the player has magic abilities or items

    }
}
