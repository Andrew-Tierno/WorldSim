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
        SimDisplay d = new SimDisplay();
        SimpleCreature tweedle = new SimpleCreature(0, 0);
        SimpleCreature dum = new SimpleCreature(50, 50);
        HunterCreature beast = new HunterCreature(80, 80);
        PlantEntity plant = new PlantEntity(0, 20);
        d.addCreature(tweedle);
        d.addCreature(dum);
        d.addCreature(beast);
        d.addCreature(plant);
    }

}
