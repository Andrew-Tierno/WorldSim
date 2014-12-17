package worldsim.entities;

import java.awt.Color;
import java.awt.Dimension;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 5, 2014
 */
public class SimpleCreature extends Creature
{
    private final int MOVE_STEP = 3;
    private final int DESTINATION_THRESHOLD = 20;
    private final int DESTINATION_THRESHOLD_SQUARED = DESTINATION_THRESHOLD * DESTINATION_THRESHOLD;
    
    public SimpleCreature(int x, int y)
    {
        super(x, y);
        destination = randomDestination();
    }

    @Override
    public Color getColor()
    {
        return Color.BLUE;
    }
    
    @Override
    public void update()
    {
        if (getDistSquared(destination) < DESTINATION_THRESHOLD_SQUARED)
            destination = randomDestination();
        double angle = Math.atan2(destination.getY() - getY(), 
                                    destination.getX() - getX());
        setX((int) (getX() + MOVE_STEP * Math.cos(angle)));
        setY((int) (getY() + MOVE_STEP * Math.sin(angle)));
    }

    public Dimension getSize()
    {
        return new Dimension(20, 20);
    }
    
    public String toString()
    {
        return "Prey";
    }
}
