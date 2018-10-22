package roguelike;

import java.awt.*;
import java.util.UUID;

public abstract class Entity {

    private String uniqueID;
    Point position;

    public Entity(Point position){
        this(position, UUID.randomUUID().toString());
    }

    public Entity(Point point, String uniqueID){
        this.position = point;
        this.uniqueID = uniqueID;
    }

    public Point getPosition(){
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entity){
            Entity entity = (Entity) obj;
            return this.uniqueID == entity.uniqueID;
        }
        return false;
    }
    
    public abstract void onContact(Entity entity, Level level);
    
 }
