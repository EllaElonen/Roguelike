package roguelike;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Player extends Actor {

	private HashSet<Item> items = new HashSet<>();
    private int lives;
    private HashMap<EquipmentSlot, Item> equipment = new HashMap<>();
    private HashMap<String, Ability> abilities = new HashMap<>();
    
    public Player(Level level, String name, int healthPoints, int speed, int intelligence, int strength) {
        super(level, name, healthPoints, speed, intelligence, strength);
    }
    //kommentar
//    public Point moveToRandomPlace() {
//		Point p;
//		if (intelligence >= 1) {
//			Random rand = new Random();
//			int x = rand.nextInt(Level.WIDTH + 1);//om olika nivåer har olika storlek?
//			int y = rand.nextInt(Level.HEIGHT + 1);//behöver man ta hänsyn till klassen Level?
//			p = new Point(x, y);
//			boolean illegalPlace = true;
//			while (illegalPlace) {
//				if (!Level.positions.contains(p) && !p.equals(this.level)) {
//					this.level = p;
//					illegalPlace = false;
//				}
//			}
//			intelligence--;
//			return p;
//		} else {
//			return this.level;
//		}
//	}
//    public Point[] throwFire() {
//		if (intelligence >= 2) {
//			Point[] p = new Point[fireLength];
//
//			if (direction==UPWARDS) {
//				for(int i = 0;i<fireLength;i++) {
//					p[i]=new Point(level.x,level.y-i-1);
//				}		
//			}else if(direction==DOWNWARDS) {
//				for(int i = 0;i<fireLength;i++) {
//					p[i]=new Point(level.x,level.y+i+1);
//				}
//			}else if(direction==LEFTWARDS) {
//				for(int i = 0;i<fireLength;i++) {
//					p[i]=new Point(level.x-i-1,level.y);
//				}
//			}else {
//				for(int i = 0;i<fireLength;i++) {
//					p[i]=new Point(level.x+i+1,level.y);
//				}
//			}
//			intelligence -= 2;
//		
//			return p;
//		} else {
//			return null;
//		}
//	}
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

	public void move(Direction direction) {
		level.moveEntity(this, direction);
	}



    public int getLives(){
	    return lives;
    }

    public void addExtraLives(int extraLives){
	    lives = extraLives + lives;
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
