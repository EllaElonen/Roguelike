package roguelike;

import java.awt.Point;

public class Item extends Entity{
	private String name;
	private EquipmentSlot slot;
	private int plusDamage;
	private int plusDefense;

	public Item(Point position, String name, EquipmentSlot slot, int plusDamage, int plusDefense) {
		super(position);
		this.name = name;
		this.slot = slot;
		this.plusDamage = plusDamage;
		this.plusDefense = plusDefense;
	}
	
	public String getName(){
		return name;
	}
	
	public EquipmentSlot getSlot() {
		return slot;
	}
	
	public int getPlusDamage(){
		return plusDamage;
	}
	
	public void onContact(Entity entity, Level level) {
		if (entity instanceof Player) {
			Player player = (Player)entity;
			player.addItemToInventory(this);
			level.removeEntity(this);
		}
	}
}
