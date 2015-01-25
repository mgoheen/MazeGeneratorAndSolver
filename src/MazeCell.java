import java.awt.Point;

/**
 * The rooms, maze cells, or vertices in the graph
 *
 * @author Michael Goheen
 */
public class MazeCell
{
    // N, E, S, W correspond to walls. True if wall exists. False if wall is
// missing.
    private boolean  N, E, S, W;
    private int      roomNumber;
    private Point    coordinates;
    private boolean  visited;
    private boolean  onPath;
    private MazeCell parent;
    private boolean  markNorth, markSouth, markEast, markWest;


    /**
     * @param coordinates
     */
    public MazeCell(Point coordinates)
    {
        N = true;
        E = true;
        S = true;
        W = true;
        parent = this;
        visited = false;
        onPath = false;
        this.coordinates = coordinates;

    }


    /**
     * @param m
     * @return true if equal
     */
    public boolean equals(MazeCell m)
    {
        return (this.getCoordinates().getX() == m.getCoordinates().getX() && this
            .getCoordinates().getY() == m.getCoordinates().getY());
    }


    public String toString()
    {
        String info = "";
        info = info + roomNumber + " - " + parent.getRoomNumber() + " ";
        return info;
    }


    // ----------------------------------------------------------
    /**
     * @param n
     *            the n to set
     */
    public void setN(boolean n)
    {
        N = n;
    }


    // ----------------------------------------------------------
    /**
     * @return the n
     */
    public boolean isN()
    {
        return N;
    }


    // ----------------------------------------------------------
    /**
     * @param e
     *            the e to set
     */
    public void setE(boolean e)
    {
        E = e;
    }


    // ----------------------------------------------------------
    /**
     * @return the e
     */
    public boolean isE()
    {
        return E;
    }


    // ----------------------------------------------------------
    /**
     * @param s
     *            the s to set
     */
    public void setS(boolean s)
    {
        S = s;
    }


    // ----------------------------------------------------------
    /**
     * @return the s
     */
    public boolean isS()
    {
        return S;
    }


    // ----------------------------------------------------------
    /**
     * @param w
     *            the w to set
     */
    public void setW(boolean w)
    {
        W = w;
    }


    // ----------------------------------------------------------
    /**
     * @return the w
     */
    public boolean isW()
    {
        return W;
    }


    /**
     * @param coordinates
     */
    public void setCoordinates(Point coordinates)
    {
        this.coordinates = coordinates;
    }


    /**
     * @return the coordinates x, y of the cell
     */
    public Point getCoordinates()
    {
        return coordinates;
    }


    /**
     * @param roomNumber
     */
    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }


    /**
     * @return the roomNumber
     */
    public int getRoomNumber()
    {
        return roomNumber;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param visited
     */
    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @return boolean
     */
    public boolean isVisited()
    {
        return visited;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param onPath
     */
    public void setOnPath(boolean onPath)
    {
        this.onPath = onPath;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @return boolean
     */
    public boolean isOnPath()
    {
        return onPath;
    }


    // ----------------------------------------------------------
    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(MazeCell parent)
    {
        this.parent = parent;
    }


    // ----------------------------------------------------------
    /**
     * @return the parent
     */
    public MazeCell getParent()
    {
        return parent;
    }


    /**
     * @param markNorth
     */
    public void setMarkNorth(boolean markNorth)
    {
        this.markNorth = markNorth;
    }


    /**
     * @return boolean
     */
    public boolean isMarkNorth()
    {
        return markNorth;
    }


    /**
     * @param markSouth
     */
    public void setMarkSouth(boolean markSouth)
    {
        this.markSouth = markSouth;
    }


    /**
     * @return boolean
     */
    public boolean isMarkSouth()
    {
        return markSouth;
    }


    /**
     * @param markEast
     */
    public void setMarkEast(boolean markEast)
    {
        this.markEast = markEast;
    }


    /**
     * @return boolean
     */
    public boolean isMarkEast()
    {
        return markEast;
    }


    /**
     * @param markWest
     */
    public void setMarkWest(boolean markWest)
    {
        this.markWest = markWest;
    }


    /**
     * @return boolean
     */
    public boolean isMarkWest()
    {
        return markWest;
    }

}
