package worldsim;

import worldsim.entities.Entity;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 11, 2014
 */
public class UpdateWorldThread extends Thread
{
    private final int SLEEP_DURATION = 10;
    private SimDisplay display;
    private World world;
    private Queue<Entity> additions, removals;

    public UpdateWorldThread(SimDisplay display, World world)
    {
        this.display = display;
        this.world = world;
        this.setName("Update World Thread");
        additions = new LinkedList<>();
        removals = new LinkedList<>();
    }

    public void run()
    {
        while (true)
        {
            LinkedList<Entity> creatures = world.getEntities();
            Queue<Entity> currAdditions = additions;
            Queue<Entity> currRemovals = removals;
            for (int i = 0; i < currAdditions.size(); i++)
                creatures.add(currAdditions.remove());
            for (int i = 0; i < currRemovals.size(); i++)
                creatures.remove(currRemovals.remove());
            synchronized(creatures)
            {
                for (int i = 0; i < creatures.size(); i++)
                {
                    Entity currEntity = creatures.get(i);
                    currEntity.update();
                }
            }
            display.repaint();
            try
            {
                sleep(SLEEP_DURATION);
            } catch (InterruptedException ex)
            {
                System.err.println("Something happened...");
                ex.printStackTrace();
            }
        }
    }
    
    public void addEntity(Entity e)
    {
        additions.add(e);
    }
    
    public void removeEntity(Entity e)
    {
        removals.add(e);
    }
}
