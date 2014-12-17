package worldsim.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedList;
import worldsim.World;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 16, 2014
 */
public class HunterCreature extends Creature
{
    private final double MAX_SEARCH_RADIUS = 75;
    private final double MAX_SEARCH_RADIUS_SQUARED = MAX_SEARCH_RADIUS * MAX_SEARCH_RADIUS;
    private final double DESTINATION_THRESHOLD = 40;
    private final double DESTINATION_THRESHOLD_SQUARED = DESTINATION_THRESHOLD * DESTINATION_THRESHOLD;
    
    public HunterCreature(int x, int y)
    {
        super(x, y);
        destination = randomDestination();
        hunger = 0;
        hungerLevel = 3;
        starvationLevel = 5;
        isHungry = false;
        moveStep = 2;
    }

    @Override
    public Color getColor()
    {
        return Color.RED;
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
        {
            destination = randomDestination();
        }
        double angle = Math.atan2(destination.getY() - getY(), destination.getX() - getX());
        setX((int) (getX() + moveStep * Math.cos(angle)));
        setY((int) (getY() + moveStep * Math.sin(angle)));
    }
    
    public void updateOnDay()
    {
        if (++hunger > starvationLevel)
            this.kill();
        else if (hunger >= hungerLevel)
            isHungry = true;
    }

    @Override
    public Dimension getSize()
    {
        return new Dimension(20, 20);
    }

    private Entity getNearestTarget()
    {
        LinkedList<Entity> creatures = World.getInstance().getEntities();
        Entity closestTarget = null;
        double distSqd;
        double minDistSqd = MAX_SEARCH_RADIUS_SQUARED + 1;
        for (Entity c : creatures)
        {
            if (c instanceof SimpleCreature)
            {
                distSqd = getDistSquared(c.getLocation());
                if (distSqd <= MAX_SEARCH_RADIUS_SQUARED && distSqd < minDistSqd)
                    closestTarget = c;
            }
        }
        return closestTarget;
    }
    
    public String toString()
    {
        return "Hunter";
    }
}
