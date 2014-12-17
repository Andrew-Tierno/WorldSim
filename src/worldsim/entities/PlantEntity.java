package worldsim.entities;

import java.awt.Color;
import java.awt.Dimension;
import worldsim.World;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 16, 2014
 */
public class PlantEntity extends Entity
{
    private int daysSinceLastPollination;
    private final int POLLINATION_CYCLE_DAYS = 5;
    private final double POLLINATION_SUCCESS_CHANCE = 0.2;
    private final int MAX_SEEDS = 3;
    private final int MAX_SEED_DISTANCE = 50;
    private final int MIN_SEED_DISTANCE = 10;
    
    public PlantEntity(int x, int y)
    {
        super(x, y);
        daysSinceLastPollination = 0;
    }

    @Override
    public Color getColor()
    {
        return Color.GREEN;
    }

    @Override
    public void updateOnTick()
    {
        if (daysSinceLastPollination > POLLINATION_CYCLE_DAYS)
        {
            if (Math.random() < POLLINATION_SUCCESS_CHANCE)
            {
                int numSeeds = (int)(Math.random() * MAX_SEEDS) + 1;
                for (int i = 0; i < 3; i++)
                {
                    double dist = Math.random() * MAX_SEED_DISTANCE + MIN_SEED_DISTANCE;
                    double angle = Math.random() * 2 * Math.PI;
                    World.getInstance().addEntity(
                            new PlantEntity((int)(dist * Math.cos(angle)) + getX(),
                                                (int)(dist * Math.sin(angle)) + getY()));
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (Exception ex) {}
                }
                daysSinceLastPollination = 0;
            }
        }
    }
    
    public void updateOnDay()
    {
        daysSinceLastPollination++;
    }

    @Override
    public Dimension getSize()
    {
        return new Dimension(10, 10);
    }

    public String toString()
    {
        return "Plant";
    }
}
