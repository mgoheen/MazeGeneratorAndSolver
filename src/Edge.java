/**
 * The edge or wall in the graph
 *
 * @author Michael Goheen
 * @version Apr 10, 2012
 */

public class Edge
{
    private int     x;
    private int     y;
    private boolean vertical;
    private boolean onPath;


    /**
     * @param x
     * @param y
     * @param vertical
     */
    public Edge(int x, int y, boolean vertical)
    {
        this.setX(x);
        this.setY(y);
        this.setVertical(vertical);
    }


    /**
     * @param x
     */
    public void setX(int x)
    {
        this.x = x;
    }


    /**
     * @return x
     */
    public int getX()
    {
        return x;
    }


    /**
     * @param y
     */
    public void setY(int y)
    {
        this.y = y;
    }


    /**
     * @return y
     */
    public int getY()
    {
        return y;
    }


    /**
     * @param vertical
     */
    public void setVertical(boolean vertical)
    {
        this.vertical = vertical;
    }


    /**
     * @return true if the edge is vertical to the point x, y
     */
    public boolean isVertical()
    {
        return vertical;
    }


    public String toString()
    {
        String info = "X: " + x + " Y: " + y + " isVertical?: " + vertical;
        return info;
    }


    // ----------------------------------------------------------
    /**
     * @param onPath the onPath to set
     */
    public void setOnPath(boolean onPath)
    {
        this.onPath = onPath;
    }


    // ----------------------------------------------------------
    /**
     * @return the onPath
     */
    public boolean isOnPath()
    {
        return onPath;
    }
}
