package roguelike;

import java.awt.*;
import java.util.UUID;

public class Actor extends Entity {
    private String name;
    protected int healthPoints;
    private int speed;
    private int intelligence;
    protected int strength;

    public Actor(Point position, String name, int healthPoints, int speed,int  intelligence, int strength){
        this(position, name, healthPoints, speed, intelligence, UUID.randomUUID().toString(), strength);
    }

    public Actor(Point position, String name, int healthPoints, int speed,int  intelligence, String id, int strength) {
        super(position, id);
        this.name = name;
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.intelligence = intelligence;
        this.strength = strength;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getStrength(){
        return strength;
    }

    private int calculateDamage(){
        return strength;
    }

    public void takeDamage(int damage){
        healthPoints -= damage;

        //TODO If the player has magic abilities or items

    }
    
	public String getSpeed() {
		return null;
	}
	
	public String getIntelligence() {
		return null;
	}

    public void onContact(Entity entity, Level level) {

    }
}
