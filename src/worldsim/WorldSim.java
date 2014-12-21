package worldsim;

import worldsim.entities.*;

/**
 * 
 * 
 * @author atierno
 *
 * @version Dec 16, 2014
 */
public class WorldSim
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        generateWorld();
    }

    public static void generateWorld()
    {
        SimDisplay d = new SimDisplay();
        int numSimple = (int)(Math.random() * 6) + 4;
        int numHunter = (int)(Math.random() * 2) + 1;
        int numPlants = (int)(Math.random() * 4) + 7;
        
        for (int i = 0; i < numSimple; i++)
            d.addCreature(new SimpleCreature((int)(World.SIZE_X * (Math.random() * 2 - 1)),
                                        (int)(World.SIZE_Y * (Math.random() * 2 - 1))));
        for (int i = 0; i < numHunter; i++)
            d.addCreature(new HunterCreature((int)(World.SIZE_X * (Math.random() * 2 - 1)),
                                        (int)(World.SIZE_Y * (Math.random() * 2 - 1))));
        for (int i = 0; i < numPlants; i++)
            d.addCreature(new PlantEntity((int)(World.SIZE_X * (Math.random() * 2 - 1)),
                                        (int)(World.SIZE_Y * (Math.random() * 2 - 1))));
        
        
    }
}
