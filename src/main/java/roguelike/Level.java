package roguelike;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import com.google.common.collect.*;
public class Level {
	public static final int WIDTH = 50;
	public static final int HEIGHT = 40;
	private BiMap<Entity, Point> entityLocations = HashBiMap.create();
	public static HashSet<Point> positions = new HashSet<>();
	
	public Level() {
		
	}
	
	public void addPosition(Point point) {
		positions.add(point);
	}
	
	public boolean positionExists(Point point) {
		return positions.contains(point);
	}
	
	public void placeEntity(Entity entity, Point point) {
		if (!positions.contains(point)) {
			throw new IllegalArgumentException("Level does not have that point.");
		}
		
		if (entityLocations.inverse().get(point) != null) {
			throw new IllegalArgumentException("Point is taken.");
		}
		
		entityLocations.put(entity, point);
	}
	
	public Point getEntityPlacement(Entity entity) {
		return entityLocations.get(entity);
	}
	
	public Entity getEntityInLocation(Point point) {
		return entityLocations.inverse().get(point);
	}
}
