package worldsim;

import worldsim.entities.Entity;
import java.util.LinkedList;
import worldsim.utils.Point;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 11, 2014
 */
public class World
{
    private LinkedList<Entity> entities;
    private static World theWorld;
    private static SimDisplay display;
    private static UpdateWorldThread updateThread;
    public static final int SIZE_X = 400;
    public static final int SIZE_Y = 400;
    public static final long TIME_PER_TICK = 10;
    public static final int TICKS_PER_WORLD_DAY = 500;
    
    private World()
    {
        entities = new LinkedList();
        if (display == null)
            display = new SimDisplay();
        updateThread = new UpdateWorldThread(display, this);
        updateThread.start();
    }
    
    public LinkedList<Entity> getEntities() { return entities; }
    
    public void addEntity(Entity e)
    { 
        updateThread.addEntity(e);
    }
    
    public void removeEntity(Entity e) 
    { 
        updateThread.removeEntity(e);
    }
    
    public LinkedList<Entity> getAllEntitiesInRadius(Point center, int radius)
    {
        LinkedList<Entity> foundEntities = new LinkedList<>();
        int radiusSquared = radius * radius;
        for (Entity e : entities)
        {
            if (center.distanceToSquared(e.getLocation()) <= radiusSquared)
            {
                foundEntities.add(e);
            }
        }
        return foundEntities;
    }
    
    public static World getInstance()
    {
        if (theWorld == null)
            theWorld = new World();
        return theWorld;
    }
    
    public static void setDisplay(SimDisplay disp)
    {
        display = disp;
    }
}
