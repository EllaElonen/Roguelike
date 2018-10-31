package roguelike;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.ArrayList;

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
//		if (!positions.contains(point)) {
//			throw new IllegalArgumentException("Level does not have that point.");
//		}
//
//		if (entityLocations.inverse().get(point) != null) {
//			throw new IllegalArgumentException("Point is taken.");
//		}

		entityLocations.put(entity, point);
	}

	public void removeEntity(Entity entity) {
		entityLocations.remove(entity);
	}

	public void emptyPosition(Point point) {
		entityLocations.inverse().remove(point);
	}

	public Point getEntityPlacement(Entity entity) {
		return entityLocations.get(entity);
	}

	public Entity getEntityInLocation(Point point) {
		return entityLocations.inverse().get(point);
	}

	public ArrayList<Point> getAvailablePlaces() {
		ArrayList<Point> availablePositions = new ArrayList<>();
		for (Point point : positions) {
			if (positionAvailable(point)) {
				availablePositions.add(point);

			}

		}
		return availablePositions;
	}

	public void moveActor(Actor actor, Direction direction) {
		Point newPoint = getPointInDirectionOf(actor, direction);
		if (positionAvailable(newPoint)) {
			entityLocations.put(actor, newPoint);
		}
	}
	
	public void movePlayer(Player player, Direction direction) {
		Point newPoint = getPointInDirectionOf(player, direction);
		Entity entityInSpot = entityLocations.inverse().get(newPoint);
		
		if (positionAvailable(newPoint)) {
			entityLocations.put(player, newPoint);
		} else if (entityInSpot != null) {
			entityInSpot.onPlayerContact(player);
		}
	}
	
	private Point getPointInDirectionOf(Entity entity, Direction direction) {
		Point entityPoint = entityLocations.get(entity);
		Point adjacentPoint = entityPoint;
		if (entityPoint != null) {
			switch (direction) {
				case UP:
					adjacentPoint = new Point(entityPoint.x, entityPoint.y + 1);
					break;
				case DOWN:
					adjacentPoint = new Point(entityPoint.x, entityPoint.y - 1);
					break;
				case RIGHT:
					adjacentPoint = new Point(entityPoint.x + 1, entityPoint.y);
					break;
				case LEFT:
					adjacentPoint = new Point(entityPoint.x - 1, entityPoint.y);
					break;
				
			}
		} else {
			adjacentPoint = entityPoint;
		}
		return adjacentPoint;
	}
		
	public boolean positionAvailable(Point point) {
		return positionExists(point) && entityLocations.inverse().get(point) == null;
	}

	public HashSet<Point> getPositions() {
		return positions;
	}

	public boolean entityExists(Entity entity) {
		return entityLocations.containsKey(entity);
	}

	public String getName() {
		return name;
	}
}
