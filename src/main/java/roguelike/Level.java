package roguelike;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

public class Level {
    HashMap <Point, Entity> entityLocations = new HashMap<Point,Entity>();
    HashSet<Point> positions = new HashSet<Point>();

    public Level(){
    }

    public boolean positionAvailable(Point position){
        boolean positionExists = positions.contains(position);
        return positionExists && entityLocations.get(position) != null;
    }

    public HashSet<Point> getPositions() {
        return positions;
    }

    public void moveEntityToPosition(Entity entity, Point position){
        if (!positions.contains(position)) {
            throw new IllegalArgumentException("This position does not exist in this level.");
        }

        entityLocations.put(position,entity);
    }

    public void addPosition(Point point){
        positions.add(point);
    }
}
