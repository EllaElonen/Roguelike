package roguelike;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Player extends Actor {

	private HashSet<Item> items = new HashSet<>();
	private int lives = 3;
	private HashMap<EquipmentSlot, Item> equipment = new HashMap<>();
	private HashMap<String, Ability> abilities = new HashMap<>();

	public Player(Level level, String name, int healthPoints, int speed, int intelligence, int strength) {
		super(level, name, healthPoints, speed, intelligence, strength);
	}

	public int calculateDefense() {
		int sum = 0;
		for (Item item : equipment.values()) {
			sum += item.getPlusDefense();
		}
		return sum / 2;
	}

	public void takeDamage(int damage) {
		int effectiveDamage = damage - calculateDefense();
		if (slotEquiped(EquipmentSlot.HELMET) && slotEquiped(EquipmentSlot.TORSO) && slotEquiped(EquipmentSlot.LEGS)) {
			effectiveDamage /= 2;
		}

		setHealthPoints(getHealthPoints() - effectiveDamage);
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
		getLevel().movePlayer(this, direction);
	}

	public int getLives() {
		return lives;
	}

	public void addExtraLives(int extraLives) {
		lives = extraLives + lives;
	}

	public void equip(Item testItem) {
		if (itemExistsInInventory(testItem))
			equipment.put(testItem.getSlot(), testItem);

	}

	public Item getSlot(EquipmentSlot slot) {
		return equipment.get(slot);

	}

	public int calculateAttack() {
		int damage = getStrength();
		if (slotEquiped(EquipmentSlot.WEAPON))
			damage += equipment.get(EquipmentSlot.WEAPON).getPlusDamage();
		return damage;
	}
	
	public void attack(Actor opponent) {
		int damage = calculateAttack();
		System.out.println(getName() + " attacks " + opponent.getName() + " for " + damage + " damage.");
		opponent.takeDamage(damage); 
		
		if (slotEquiped(EquipmentSlot.WEAPON) && slotEquiped(EquipmentSlot.LEGS))
			chargeAttack(opponent);
		
		if (slotEquiped(EquipmentSlot.WEAPON) && slotEquiped(EquipmentSlot.SHIELD))
			shieldBash(opponent);
		
		if (slotEquiped(EquipmentSlot.HELMET) && slotEquiped(EquipmentSlot.WEAPON)) 
			counterAttack(opponent);
		
		if(!opponent.isAlive())
			System.out.println(getName() + " has slain " + opponent.getName() + "!");
			opponent.getLevel().removeEntity(opponent);
	}
	
	private void chargeAttack(Actor opponent) {
		int damage = equipment.get(EquipmentSlot.WEAPON).getPlusDamage();
		System.out.println(getName() + " charges " + opponent.getName() + " for " + damage + " damage.");
		
		opponent.takeDamage(damage);
	}
	
	private void shieldBash(Actor opponent) {
		int damage = equipment.get(EquipmentSlot.SHIELD).getPlusDamage();
		System.out.println(getName() + " shield bashes " + opponent.getName() + " for " + damage + " damage.");
		
		opponent.takeDamage(damage);
	}
	
	private void counterAttack(Actor opponent) {
		int damage = opponent.calculateAttack() / 5;
		System.out.println(getName() + " counterattacks " + opponent.getName() + " for " + damage + " damage.");
		
		opponent.takeDamage(damage);
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
			ability.use(this, getLevel());
		}
	}
	public boolean isAlive() {
		return getHealthPoints() > 0&&lives>0;

	}
	
}
