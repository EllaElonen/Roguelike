package roguelike;

import java.awt.Point;

public class Item extends Entity{
	String name;

	public Item(Point position, String name) {
		super(position, name);
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	public String toString() {
		return name;
	}
	
	public void onContact(Entity entity, Level level) {
		if (entity instanceof Player) {
			Player player = (Player)entity;
			player.addItemToInventory(this);
			level.removeEntity(this);
		}
	}
}
