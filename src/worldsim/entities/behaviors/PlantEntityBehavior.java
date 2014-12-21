package worldsim.entities.behaviors;

import worldsim.World;
import worldsim.entities.PlantEntity;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 20, 2014
 */
public class PlantEntityBehavior extends EntityBehavior
{
    private int daysSinceLastPollination;
    private final int POLLINATION_CYCLE_DAYS = 5;
    private final double POLLINATION_SUCCESS_CHANCE = 0.3;
    private final int MAX_SEEDS = 2;
    private final int MAX_SEED_DISTANCE = 50;
    private final int MIN_SEED_DISTANCE = 10;
    private final PlantEntity thePlant;
    
    public PlantEntityBehavior(PlantEntity plant)
    {
        thePlant = plant;
        daysSinceLastPollination = 0;
    }
    
    @Override
    public void updateOnTick()
    {
        if (daysSinceLastPollination > POLLINATION_CYCLE_DAYS)
        {
            if (Math.random() < POLLINATION_SUCCESS_CHANCE)
            {
                int numSeeds = (int)(Math.random() * MAX_SEEDS) + 1;
                for (int i = 0; i < numSeeds; i++)
                {
                    double dist = Math.random() * MAX_SEED_DISTANCE + MIN_SEED_DISTANCE;
                    double angle = Math.random() * 2 * Math.PI;
                    World.getInstance().addEntity(
                            new PlantEntity((int)(dist * Math.cos(angle)) + thePlant.getX(),
                                                (int)(dist * Math.sin(angle)) + thePlant.getY()));
                }
                daysSinceLastPollination = 0;
            }
        }
    }
    
    @Override
    public void updateOnDay()
    {
        daysSinceLastPollination++;
    }
}
