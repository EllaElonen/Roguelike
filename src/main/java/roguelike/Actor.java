package roguelike;

import java.awt.*;
import java.util.UUID;

public class Actor extends Entity {
    private String name;
    protected int healthPoints;
    private int speed;
    protected int intelligence;
    protected int strength;

    public Actor(Level level, String name, int healthPoints, int speed,int  intelligence, int strength){
        this(level, name, healthPoints, speed, intelligence, UUID.randomUUID().toString(), strength);
    }

    public Actor(Level level, String name, int healthPoints, int speed,int  intelligence, String id, int strength) {
        super(level, id);
        
        if (healthPoints < 0 || healthPoints > 99) {
        		throw new IllegalArgumentException();
        }
        
        if (strength < 0 || strength > 99) {
        		throw new IllegalArgumentException();
        }
        
        if (speed < 0 || speed > 99) {
        		throw new IllegalArgumentException();
        }
        
        if (intelligence < 0 || intelligence > 99) {
        		throw new IllegalArgumentException();
        }
        
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

    public int calculateDamage(){
        return strength;
    }

    public void takeDamage(int damage){
        healthPoints -= damage;

        //TODO If the player has magic abilities or items

    }
  
    public String getStats() {
		return "healthPoints: " + getHealthPoints() + "\nSpeed: " + getSpeed() + "\nIntelligence: " + getIntelligence() + "\nStrength: " + getStrength();
	}
    
	public int getSpeed() {
		return speed;
	}
	
	public int getIntelligence() {
		return intelligence;
	}
	
    public void onContact(Entity entity, Level level) {
    		if (entity instanceof Player) {
    			Player opponent = (Player)entity;
    			tradeBlows(opponent);
    			
    			if(!opponent.isAlive()) {
    				level.removeEntity(opponent);
    			}
    			if(!isAlive()) {
    				level.removeEntity(this);
    			}
    		}
    }
    
    private void tradeBlows(Player opponent) {
	    	opponent.takeDamage(calculateDamage());
		takeDamage(opponent.calculateDamage());
		
		if (opponent.slotEquiped(EquipmentSlot.HELMET) && opponent.slotEquiped(EquipmentSlot.WEAPON)) {
			takeDamage(calculateDamage() / 5);
		}
    }
    
    public boolean isAlive() {
    		return healthPoints > 0;
    }
}
