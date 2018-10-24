package roguelike;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;

public class Player extends Actor {

	private HashSet<Item> items = new HashSet<>();
    private int extraLives;
    private HashMap<EquipmentSlot, Item> equipment = new HashMap<>();

    public Player(Point position, String name, int healthPoints, int speed, int intelligence, int strength) {
        super(position, name, healthPoints, speed, intelligence, strength);
    }
    public int calculateDefense() {
    	int i = 0;
    	for(Item item: equipment.values()) {
    		i+=item.getPlusDefense();
    	}
    	return i;
    }
    public void takeDamage(int damage) {
    	int effectiveDamage=damage-calculateDefense()/2;
    	setHealthPoints(healthPoints-effectiveDamage);
    	 
    }
	public boolean addItemToInventory(Item item) {
		if (items.size() < 30) {
			items.add(item);
			return true;
		}

		return false;

	}

	public boolean itemExistsInInventory(Item item) {
		return items.contains(item);
	}

	public int getInventorySize() {
		return items.size();

	}

	// ger referenser till alla Items
	public HashSet<Item> getItemsFromInventory() {
		return items;

	}

	public boolean dropItemFromInventory(Item item) {
		if (items.contains(item)) {
			items.remove(item);
			return true;
		}

		return false;

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

	public void equip(Item testItem) {
		if (itemExistsInInventory(testItem)) 
			equipment.put(testItem.getSlot(), testItem);
	
	}

	public Item getSlot(EquipmentSlot slot) {
		return equipment.get(slot);
		
	}
	
	public int calculateDamage(){	
		Item equipedWeapon = equipment.get(EquipmentSlot.WEAPON);
		int damage = strength;
		if (equipedWeapon != null) {
			damage += equipedWeapon.getPlusDamage();
		}
			
        return damage;
    }
}
