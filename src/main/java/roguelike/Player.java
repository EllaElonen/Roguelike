package roguelike;

import java.util.HashMap;
import java.util.HashSet;

public class Player extends Actor {

	private HashSet<Item> items = new HashSet<>();
    private int lives=3;
    private HashMap<EquipmentSlot, Item> equipment = new HashMap<>();
    private HashMap<String, Ability> abilities = new HashMap<>();
    
    public Player(Level level, String name, int healthPoints, int speed, int intelligence, int strength) {
        super(level, name, healthPoints, speed, intelligence, strength);
    }

    public int calculateDefense() {
    		int i = 0;
	    	for(Item item: equipment.values()) {
	    		i+=item.getPlusDefense();
	    	}
    		return i;
    }
    
    public void takeDamage(int damage) {
    		int effectiveDamage = damage - calculateDefense() / 2;
    		if (slotEquiped(EquipmentSlot.HELMET) && slotEquiped(EquipmentSlot.TORSO) && slotEquiped(EquipmentSlot.LEGS)) {
    			effectiveDamage /= 2;
    		}
    		
    		setHealthPoints(healthPoints-effectiveDamage);
    }
	public int calculateAttack(){	
		Item equipedWeapon = equipment.get(EquipmentSlot.WEAPON);
		Item equipedShield = equipment.get(EquipmentSlot.SHIELD);
		int damage = strength;
		if(slotEquiped(EquipmentSlot.WEAPON)) {
			damage += equipedWeapon.getPlusDamage();
			if(slotEquiped(EquipmentSlot.LEGS)) {
				damage += equipedWeapon.getPlusDamage();
			}
			if(slotEquiped(EquipmentSlot.SHIELD)) {
				damage += equipedShield.getPlusDamage(); 
			}
		}
	
        return damage;
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
	    lives += extraLives ;
    }

	public void equip(Item testItem) {
		if (itemExistsInInventory(testItem)) 
			equipment.put(testItem.getSlot(), testItem);
	
	}

	public Item getSlot(EquipmentSlot slot) {
		return equipment.get(slot);
		
	}
	

	
	public boolean slotEquiped(EquipmentSlot slot) {
		return equipment.containsKey(slot);
	}
	
	public void learnAbility(Ability ability) {
		abilities.put(ability.getName(), ability);
	}
	
	public void castAbility(String abilityName) {
		Ability ability = abilities.get(abilityName);
		if (ability != null) {
			ability.use(this, level);
		}
	}
}
