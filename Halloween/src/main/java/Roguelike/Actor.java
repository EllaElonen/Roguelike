package Roguelike;

import java.awt.*;
import java.util.UUID;

public class Actor extends Entity{
    private String name;
    private int healthPoints;
    private int speed;
    private int intelligence;

    public Actor(Point position, String name, int healthPoints, int speed,int  intelligence){
        this(position, name, healthPoints, speed, intelligence, UUID.randomUUID().toString());
    }

    public Actor(Point position, String name, int healthPoints, int speed,int  intelligence, String id) {
        super(position, id);
        this.name = name;
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.intelligence = intelligence;
    }
}
