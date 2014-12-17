package worldsim.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import worldsim.World;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 16, 2014
 */
public abstract class Creature extends Entity
{
    protected Point destination;
    protected int hungerLevel;
    protected int starvationLevel;
    
    public Creature(int x, int y)
    {
        super(x, y);
    }
    
    protected double getDistSquared(Point p)
    {
        return (p.getX() - getX()) * (p.getX() - getX()) +
                            (p.getY() - getY()) * (p.getY() - getY());
    }
    
    protected Point randomDestination()
    {
        return new Point((int)(2 * World.SIZE_X * Math.random()) - World.SIZE_X,
                            (int)(2 * World.SIZE_Y * Math.random()) - World.SIZE_Y);
    }
}
