package worldsim.entities;

import java.awt.Point;
import java.util.List;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 16, 2014
 */
public abstract class Creature extends Entity
{
    protected Point destination;
    protected boolean isHungry;
    protected int currHunger;
    protected int hungryLevel;
    protected int starvationLevel;
    protected int moveStep;
    protected int fovRadius;
    protected List<EntityType> predators;
    protected List<EntityType> prey;
    
    public Creature(int x, int y, EntityType type, int foodValue,
                    int moveStep, int hungryLevel, int starvationLevel,
                    int fovRadius, List<EntityType> predators, List<EntityType> prey)
    {
        super(x, y, type, foodValue);
        this.moveStep = moveStep;
        this.hungryLevel = hungryLevel;
        this.starvationLevel = starvationLevel;
        this.fovRadius = fovRadius;
        this.predators = predators;
        this.prey = prey;
        currHunger = 0;
        isHungry = false;
    }
    
    public List<EntityType> getPredators() { return predators; }
    public List<EntityType> getPrey() { return prey; }
    public int getFOV() { return fovRadius; }
    public int getHungryLevel() { return hungryLevel; }
    public int getStarvationLevel() { return starvationLevel; }
    public int getCurrHunger() { return currHunger; }
    public int getMoveStep() { return moveStep; }
    
    public void addHunger() { currHunger++; }
    public void addHunger(int amt) { currHunger += amt; }
    
    public void rmvHunger() 
    { 
        if (currHunger > 0)
            currHunger--; 
    }
    public void rmvHunger(int amt) { 
        if (currHunger - amt > 0)
            currHunger -= amt;
        else
            resetHunger();
    }
    
    public void resetHunger() { currHunger = 0; }
    
    public boolean isHungry() { return currHunger >= hungryLevel; }
    public boolean isStarving() { return currHunger >= starvationLevel; }
}
