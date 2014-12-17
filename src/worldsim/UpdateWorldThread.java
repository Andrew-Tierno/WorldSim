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
    private SimDisplay display;
    private World world;
    private Queue<Entity> additions, removals;
    private int ticksSinceLastDay;
    private int currDay;

    public UpdateWorldThread(SimDisplay display, World world)
    {
        this.display = display;
        this.world = world;
        this.setName("Update World Thread");
        additions = new LinkedList<>();
        removals = new LinkedList<>();
        ticksSinceLastDay = 0;
        currDay = 0;
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
            for (Entity e : creatures)
            {
                e.updateOnTick();
            }
            if(++ticksSinceLastDay % World.TICKS_PER_WORLD_DAY == 0)
            {
                System.out.println("Day " + ++currDay);
                currDay++;
                for (Entity e : creatures)
                    e.updateOnDay();
                ticksSinceLastDay = 0;
            }
            display.repaint();
            try
            {
                sleep(World.TIME_PER_TICK);
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
