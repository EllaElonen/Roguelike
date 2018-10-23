package roguelike;

import java.awt.Point;

public class Item extends Entity{
	private String name;
	private EquipmentSlot slot;

	public Item(Point position, String name, EquipmentSlot slot) {
		super(position, name);
		this.name = name;
		this.slot = slot;
	}
	
	public String getName(){
		return name;
	}

	public String toString() {
		return name;
	}
	
	public EquipmentSlot getSlot() {
		return slot;
	}
	
	public void onContact(Entity entity, Level level) {
		if (entity instanceof Player) {
			Player player = (Player)entity;
			player.addItemToInventory(this);
			level.removeEntity(this);
		}
	}
}
