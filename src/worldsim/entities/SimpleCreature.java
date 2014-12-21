package worldsim.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import worldsim.entities.behaviors.BasicCreatureBehavior;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 5, 2014
 */
public class SimpleCreature extends Creature
{   
    public SimpleCreature(int x, int y)
    {
        super(x, y, EntityType.SIMPLE, 2, 4, 4, 7, 75, Arrays.asList(EntityType.HUNTER), 
                                                    Arrays.asList(EntityType.PLANT));
        behavior = new BasicCreatureBehavior(this);
    }

    @Override
    public Color getColor()
    {
        return Color.BLUE;
    }

    @Override
    public Dimension getSize()
    {
        return new Dimension(20, 20);
    }
    
    @Override
    public String toString()
    {
        return "Prey";
    }
}
