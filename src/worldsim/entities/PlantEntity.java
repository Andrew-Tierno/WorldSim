package worldsim.entities;

import java.awt.Color;
import java.awt.Dimension;
import worldsim.entities.behaviors.PlantEntityBehavior;
/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 16, 2014
 */
public class PlantEntity extends Entity
{
    public PlantEntity(int x, int y)
    {
        super(x, y, EntityType.PLANT, 1);
        behavior = new PlantEntityBehavior(this);
    }

    @Override
    public Color getColor()
    {
        return Color.GREEN;
    }

    @Override
    public Dimension getSize()
    {
        return new Dimension(10, 10);
    }

    @Override
    public String toString()
    {
        return "Plant";
    }
}
