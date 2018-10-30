package roguelike;

import java.awt.Point;

public class Item extends Entity{
	private String name;
	private EquipmentSlot slot;
	private int plusDamage;
	private int plusDefense;

	public Item(Level level, String name, EquipmentSlot slot, int plusDamage, int plusDefense) {
		super(level);
		this.name = name;
		this.slot = slot;
		this.plusDamage = plusDamage;
		this.plusDefense = plusDefense;
	}
	
	public EquipmentSlot getSlot() {
		return slot;
	}
	
	public int getPlusDamage(){
		return plusDamage;
	}
	
	public void onPlayerContact(Player player) {
			player.addItemToInventory(this);
			getLevel().removeEntity(this);
	}

	public int getPlusDefense() {
		return plusDefense;
	}
}
