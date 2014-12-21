package worldsim.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import worldsim.entities.behaviors.BasicCreatureBehavior;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 16, 2014
 */
public class HunterCreature extends Creature
{    
    public HunterCreature(int x, int y)
    {
        super(x, y, EntityType.HUNTER, 5, 2, 3, 5, 
                75, Arrays.asList(EntityType.NONE), Arrays.asList(EntityType.SIMPLE));
        behavior = new BasicCreatureBehavior(this);
    }

    @Override
    public Color getColor()
    {
        return Color.RED;
    }

    @Override
    public Dimension getSize()
    {
        return new Dimension(20, 20);
    }
    
    @Override
    public String toString()
    {
        return "Hunter";
    }
}
