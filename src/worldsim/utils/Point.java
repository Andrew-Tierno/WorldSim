package worldsim.utils;

/**
 * 
 * 
 * @author Andrew Tierno
 * @version Dec 20, 2014
 */
public class Point
{
    private int x, y;
    
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    public int distanceToSquared(Point p)
    {
        return (int)((getX() - p.getX()) * (getX() - p.getX()) +
                (getY() - p.getY()) * (getY() - p.getY()));
    }
    
    public void rotate(double radians)
    {
        int newX = (int)(x * Math.cos(radians) - y * Math.sin(radians));
        int newY = (int)(x * Math.sin(radians) + y * Math.cos(radians));
        
        x = newX;
        y = newY;
    }
    
    public void scale(double scaleX, double scaleY)
    {
        x *= scaleX;
        y *= scaleY;
    }
    
    public void scale(double scale)
    {
        scale(scale, scale);
    }
    
    public void scaleX(double scaleX)
    {
        scale(scaleX, 1);
    }
    
    public void scaleY(double scaleY)
    {
        scale(1, scaleY);
    }
    
    public double angleTo(Point p)
    {
        return Math.atan2(p.getY() - getY(), p.getX() - getX());
    }
}
