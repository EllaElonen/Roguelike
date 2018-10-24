package roguelike;

import java.awt.*;
import java.util.UUID;

public abstract class Entity {

    private String uniqueID;
  //  private Level level;
   protected Level level;

    public Entity(Level level){
        this(level, UUID.randomUUID().toString());
    }

    public Entity(Level level, String uniqueID){
        this.level = level;
        this.uniqueID = uniqueID;
    }

    public Level getLevel(){
        return level;
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
