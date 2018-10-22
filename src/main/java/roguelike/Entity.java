package roguelike;

import java.awt.*;
import java.util.UUID;

public abstract class Entity {

    private String uniqueID;
    private Point position;

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
            if (this.uniqueID == entity.uniqueID){
                return true;
            }
        }
        return false;
    }
    
    public abstract void onContact(Entity entity, Level level);
 }
