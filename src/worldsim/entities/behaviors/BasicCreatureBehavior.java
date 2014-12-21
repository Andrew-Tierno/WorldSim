package worldsim.entities.behaviors;

import java.util.LinkedList;
import worldsim.World;
import worldsim.entities.Creature;
import worldsim.entities.Entity;
import worldsim.utils.Point;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 20, 2014
 */
public class BasicCreatureBehavior extends EntityBehavior
{
    private final Creature theCreature;
    private final int DESTINATION_THRESHOLD = 20;
    private final int DESTINATION_THRESHOLD_SQUARED = DESTINATION_THRESHOLD * DESTINATION_THRESHOLD;
    private Point destination;
    
    public BasicCreatureBehavior(Creature c)
    {
        theCreature = c;
        destination = randomDestination();
    }

    @Override
    public void updateOnTick()
    {
        Entity nearestPrey = getNearestPrey();
        Entity nearestPredator = getNearestPredator();
        if (theCreature.isHungry())
        {
            if (nearestPrey != null)
            {
                if (theCreature.getLocation().distanceToSquared(nearestPrey.getLocation())
                                < DESTINATION_THRESHOLD_SQUARED)
                {
                    theCreature.rmvHunger(nearestPrey.getFoodValue());
                    nearestPrey.kill();
                    destination = randomDestination();
                }
                else if (nearestPredator != null)
                {
                    double riskChance = theCreature.getCurrHunger() / theCreature.getStarvationLevel();
                    if (Math.random() < riskChance)
                        destination = nearestPrey.getLocation();
                    else
                    {
                        int currDistToPredator = nearestPredator.getLocation().distanceToSquared(
                                                    theCreature.getLocation());
                        int destDistToPredator = nearestPredator.getLocation().distanceToSquared(
                                                    destination);
                        if (destDistToPredator < currDistToPredator)
                        {
                            double currAngleToPredator = theCreature.getLocation().angleTo(
                                                            nearestPredator.getLocation());

                            destination.rotate(-currAngleToPredator * (1 - riskChance));
                        }
                    }
                }
                else
                    destination = nearestPrey.getLocation();
            }
        }
        if (nearestPredator != null)
        {
            int currDistToPredator = nearestPredator.getLocation().distanceToSquared(
                                        theCreature.getLocation());
            int destDistToPredator = nearestPredator.getLocation().distanceToSquared(
                                        destination);
            if (destDistToPredator < currDistToPredator)
            {
                double currAngleToPredator = theCreature.getLocation().angleTo(
                                                nearestPredator.getLocation());

                destination.rotate(-currAngleToPredator);
            }
        }
        if (theCreature.getLocation().distanceToSquared(destination)
                                            < DESTINATION_THRESHOLD_SQUARED)
        {
            destination = randomDestination();
        }
        double angle = Math.atan2(destination.getY() - theCreature.getY(), 
                                    destination.getX() - theCreature.getX());
        theCreature.move((int)(theCreature.getMoveStep() * Math.cos(angle)),
                (int) (theCreature.getMoveStep() * Math.sin(angle)));
    }

    @Override
    public void updateOnDay()
    {
        theCreature.addHunger();
        if (theCreature.isStarving())
        {
            double deathChance = (theCreature.getStarvationLevel() - theCreature.getCurrHunger()) * .2;
            if (Math.random() < deathChance)
                theCreature.kill();
        }
    }

    private Entity getNearestPredator()
    {
        Entity nearestPredator = null;
        int minDistSqd = theCreature.getFOV() * theCreature.getFOV() + 1;
        LinkedList<Entity> allEntities = 
                World.getInstance().getAllEntitiesInRadius(theCreature.getLocation(), 
                                                            theCreature.getFOV());
        for (Entity e : allEntities)
        {
            if (theCreature.getPredators().contains(e.getType()))
            {
                int currDistSqd = 
                        theCreature.getLocation().distanceToSquared(e.getLocation());
                if (currDistSqd < minDistSqd)
                {
                    minDistSqd = currDistSqd;
                    nearestPredator = e;
                }
            }
        }
        return nearestPredator;
    }
    
    private Entity getNearestPrey()
    {
        Entity nearestPrey = null;
        int minDistSqd = theCreature.getFOV() * theCreature.getFOV() + 1;
        LinkedList<Entity> allEntities = 
                World.getInstance().getAllEntitiesInRadius(theCreature.getLocation(), 
                                                            theCreature.getFOV());
        for (Entity e : allEntities)
        {
            if (theCreature.getPrey().contains(e.getType()))
            {
                int currDistSqd = 
                        theCreature.getLocation().distanceToSquared(e.getLocation());
                if (currDistSqd < minDistSqd)
                {
                    minDistSqd = currDistSqd;
                    nearestPrey = e;
                }
            }
        }
        return nearestPrey;
    }
    
    private Point randomDestination()
    {
        return new Point((int)(2 * World.SIZE_X * Math.random()) - World.SIZE_X,
                            (int)(2 * World.SIZE_Y * Math.random()) - World.SIZE_Y);
    }
}
