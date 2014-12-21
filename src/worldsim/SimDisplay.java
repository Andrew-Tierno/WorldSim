package worldsim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import worldsim.entities.Entity;
import worldsim.utils.Point;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 5, 2014
 */
public class SimDisplay extends JComponent
{
    protected JFrame frame;
    private final Color BACKGROUND_COLOR = Color.WHITE;
    
    public SimDisplay()
    {
        frame = new JFrame("World Sim");
        frame.setBounds(0, 0, World.SIZE_X * 2, World.SIZE_Y * 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
        World.setDisplay(this);
    }
    
    public synchronized void addCreature(Entity c) { World.getInstance().addEntity(c); }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(BACKGROUND_COLOR);
        g2.fillRect(0, 0, getWidth(), getHeight());
        LinkedList<Entity> creatures = World.getInstance().getEntities();
        synchronized(creatures)
        {
            for (Entity c : creatures)
            {
                g2.setColor(c.getColor());
                Dimension creatureSize = c.getSize();
                Point displayPoint = convertWorldPoint(c.getLocation());
                g2.fillRect(displayPoint.getX(), displayPoint.getY(), 
                        creatureSize.width, creatureSize.height);
            } 
        }
    }
    
    private Point convertWorldPoint(Point worldPoint)
    {
        return new Point(worldPoint.getX() + getWidth() / 2, - worldPoint.getY() + getHeight() / 2);
    }
}
