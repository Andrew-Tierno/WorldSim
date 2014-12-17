package worldsim.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import worldsim.World;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 5, 2014
 */
public class SimpleCreature extends Creature
{
    private final double MAX_SEARCH_RADIUS = 75;
    private final double MAX_SEARCH_RADIUS_SQUARED = MAX_SEARCH_RADIUS * MAX_SEARCH_RADIUS;
    private final int DESTINATION_THRESHOLD = 40;
    private final int DESTINATION_THRESHOLD_SQUARED = DESTINATION_THRESHOLD * DESTINATION_THRESHOLD;
    
    public SimpleCreature(int x, int y)
    {
        super(x, y);
        destination = randomDestination();
        hunger = 0;
        hungerLevel = 4;
        starvationLevel = 7;
        moveStep = 4;
    }

    @Override
    public Color getColor()
    {
        return Color.BLUE;
    }
    
    @Override
    public void updateOnTick()
    {
        if (isHungry)
        {
            Entity target = getNearestTarget();
            if (target != null)
            {
                destination = target.getLocation();
                if (getDistSquared(destination) < DESTINATION_THRESHOLD_SQUARED)
                {
                    target.kill();
                    hunger = 0;
                    isHungry = false;
                }
            }
        }
        if (getDistSquared(destination) < DESTINATION_THRESHOLD_SQUARED)
            destination = randomDestination();
        double angle = Math.atan2(destination.getY() - getY(), destination.getX() - getX());
        setX((int) (getX() + moveStep * Math.cos(angle)));
        setY((int) (getY() + moveStep * Math.sin(angle)));
    }
    
    private Entity getNearestTarget()
    {
        LinkedList<Entity> creatures = World.getInstance().getEntities();
        Entity closestTarget = null;
        double distSqd;
        double minDistSqd = MAX_SEARCH_RADIUS_SQUARED + 1;
        for (Entity c : creatures)
        {
            if (c instanceof PlantEntity)
            {
                distSqd = getDistSquared(c.getLocation());
                if (distSqd <= MAX_SEARCH_RADIUS_SQUARED && distSqd < minDistSqd)
                    closestTarget = c;
            }
        }
        return closestTarget;
    }
    
    public void updateOnDay()
    {
        if (++hunger > starvationLevel)
            this.kill();
        else if (hunger >= hungerLevel)
            isHungry = true;
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
