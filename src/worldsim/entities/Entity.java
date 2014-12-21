package worldsim.entities;

import java.awt.Color;
import java.awt.Dimension;
import worldsim.World;
import worldsim.utils.Point;
import worldsim.entities.behaviors.EntityBehavior;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 5, 2014
 */
public abstract class Entity
{
    private int posX, posY;
    private final EntityType type;
    protected EntityBehavior behavior;
    protected int foodValue;
    
    public Entity(int posX, int posY, EntityType type, int foodValue)
    {
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        this.foodValue = foodValue;
        
    }
    
    public int getX() { return posX; }
    public int getY() { return posY; }
    public EntityType getType() { return type; }
    public Point getLocation() { return new Point(posX, posY); }
    public int getFoodValue() { return foodValue; }
    
    public void setX(int newX) { posX = newX; }
    public void setY(int newY) { posY = newY; }
    public void moveX(int dx) { posX += dx; }
    public void moveY(int dy) { posY += dy; }
    public void move(int dx, int dy)
    {
        posX += dx;
        posY += dy;
    }
    
    public void kill()
    {
        World.getInstance().removeEntity(this);
    }
    
    public void updateOnTick()
    {
        behavior.updateOnTick();
    }

    public void updateOnDay()
    {
        behavior.updateOnDay();
    }
    
    public abstract Color getColor();
    public abstract Dimension getSize();
    public abstract String toString();
}
