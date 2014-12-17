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
    private int timeSincePollenation;
    private final int POLLENATION_CYCLE_TIME = 500;
    private final double POLLENATION_SUCCESS_CHANCE = 0.6;
    private final int MAX_SEEDS = 3;
    private final int MAX_SEED_DISTANCE = 50;
    private final int MIN_SEED_DISTANCE = 10;
    
    public PlantEntity(int x, int y)
    {
        super(x, y);
        timeSincePollenation = 0;
    }

    @Override
    public Color getColor()
    {
        return Color.GREEN;
    }

    @Override
    public void update()
    {
        if (timeSincePollenation > POLLENATION_CYCLE_TIME)
        {
            if (Math.random() < POLLENATION_SUCCESS_CHANCE)
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
                timeSincePollenation = 0;
            }
        }
        else
            timeSincePollenation++;
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
