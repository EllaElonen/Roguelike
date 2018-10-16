import Roguelike.Actor;
import Roguelike.Level;

import javax.swing.text.Position;
import java.awt.*;
import java.util.UUID;

public class Player extends Actor {

    private Level level;

    public Player(Point position, String name, int healthPoints, int speed, int intelligence) {
        super(position, name, healthPoints, speed, intelligence);
    }

    public void moveUp(Level level ){
        //this.level = level;
        double newX = getPosition().getX();
        double newY = getPosition().getY() + 1.0;
        Point newPoint = new Point((int) newX, (int) newY);
    }
}
