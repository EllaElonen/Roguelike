package roguelike;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.awt.*;
import java.util.HashSet;
public class Level {
	private String name;
	private BiMap<Entity, Point> entityLocations = HashBiMap.create();

	private HashSet<Point> positions = new HashSet<>();

	public Level(String name) {
		this.name = name;
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
	
	public void removeEntity(Entity entity) {
		entityLocations.remove(entity);
	}
	
	public Point getEntityPlacement(Entity entity) {
		return entityLocations.get(entity);
	}
	
	public Entity getEntityInLocation(Point point) {
		return entityLocations.inverse().get(point);
	}
	
	public void moveEntity(Entity entity, Direction direction) {
		Point oldPoint = entityLocations.get(entity);
		if (oldPoint != null) {
			Point newPoint;
			switch(direction) {
				case UP:
					newPoint = new Point(oldPoint.x, oldPoint.y + 1);
					break;
				case DOWN:
					newPoint = new Point(oldPoint.x, oldPoint.y - 1);
					break;
				case RIGHT:
					newPoint = new Point(oldPoint.x + 1, oldPoint.y);
					break;
				case LEFT:
					newPoint = new Point(oldPoint.x - 1, oldPoint.y);
					break;
				default:
					newPoint = oldPoint;
			}
			Entity entityInSpot = entityLocations.inverse().get(newPoint);
			if (entityInSpot != null) {
				entityInSpot.onContact(entity, this);
			} else if (positionAvailable(newPoint)){
				entityLocations.put(entity, newPoint);
			}
		}
	}
	
	private boolean positionAvailable(Point point) {
		return positionExists(point) && entityLocations.inverse().get(point) == null;
	}
	
	public HashSet<Point> getPositions() {
		return positions;
	}
	
	public boolean entityExists(Entity entity) {
		return entityLocations.containsValue(entity);
	}
	
	public String getName() {
		return name;
	}
}
