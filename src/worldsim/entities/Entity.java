package worldsim.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import worldsim.World;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 5, 2014
 */
public abstract class Entity
{
    private int posX, posY;
    
    public Entity(int x, int y)
    {
        posX = x;
        posY = y;
        
    }
    
    public int getX() { return posX; }
    public int getY() { return posY; }
    
    public void setX(int newX) { posX = newX; }
    public void setY(int newY) { posY = newY; }
    
    public Point getLocation() { return new Point(posX, posY); }
    
    public void kill()
    {
        World.getInstance().removeEntity(this);
    }
    
    public abstract Color getColor();
    public abstract void updateOnTick();
    public abstract void updateOnDay();
    public abstract Dimension getSize();
    public abstract String toString();
}
