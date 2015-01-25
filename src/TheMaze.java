import java.awt.Point;

// -------------------------------------------------------------------------
/**
 * Holds mazeRoom data
 *
 * @author Michael Goheen
 * @version Apr 10, 2012
 */
public class TheMaze
{
    private MazeCell[][]    mazeRoom;
    private int             columns, rows, totalWalls;
    private int             rooms;
    private Edge[]          internalWalls;
    private int             internalWallsRemaining;
    private DisjointSet     roomDS;
    private Queue<MazeCell> path       = new Queue<MazeCell>();
    private int             pathLength = 1;
    private int             pathsFound = 0;


    // ----------------------------------------------------------
    /**
     * Create a new mazeRoom with default size
     */
    public TheMaze()
    {
        this(40, 40);
    }


    // ----------------------------------------------------------
    /**
     * Create a new mazeRoom with specified size.
     *
     * @param columns
     * @param rows
     */
    public TheMaze(int columns, int rows)
    {
        if (columns < 0 || rows < 0)
        {
            System.out
                .println("ERROR: Columns and rows must be greater than 0.");
        }
        else
        {

            internalWallsRemaining =
                (columns * (rows + 1) + rows * (columns + 1))
                    - (2 * columns + 2 * rows);
            setColumns(columns);
            setRows(rows);
            setRooms();
            roomDS = new DisjointSet(rooms);
            setWalls(columns * (rows + 1) + rows * (columns + 1));
            // Internal Walls
            internalWalls = new Edge[this.getInternalWallsRemaining()];
            mazeRoom = new MazeCell[columns][rows];
            // Initialize Array Cells
            int roomNumber = 0;
            for (int x = 0; x < mazeRoom.length; x++)
            {
                for (int y = 0; y < mazeRoom[x].length; y++)
                {
                    mazeRoom[x][y] = new MazeCell(new Point(x, y));
                    mazeRoom[x][y].setRoomNumber(roomNumber++);
                }
            }

            // System.out.println("mazeRoom Array Length: " + mazeRoom.length);
            // Populate array with internal walls on the vertical
            int wallArrayIndex = 0;
            for (int x = 1; x < mazeRoom.length + 1; x++)
            {
                for (int y = 1; y < mazeRoom[x - 1].length; y++)
                {
                    internalWalls[wallArrayIndex] = new Edge(x, y, true);
                    wallArrayIndex++;
                }
            }
            // Populate array with internal walls on the horizontal
            // wallArrayIndex = 0;
            for (int x = 1; x < mazeRoom.length; x++)
            {
                for (int y = 1; y < mazeRoom[x].length + 1; y++)
                {
                    internalWalls[wallArrayIndex] = new Edge(x, y, false);
                    wallArrayIndex++;
                }
            }
            // Remove walls without cycle
            for (int i = 0; i < internalWalls.length; i++)
            {
                removeWall();
            }
        }
    }


    /**
     * Remove a wall to create cycles
     */
    public void removeWallForCycles()
    {
        if (internalWallsRemaining > 0)
        {
            // Remove a random wall
            int randomNum =
                (int)(Math.random() * this.getInternalWallsRemaining());
            // Fix boundary cases of 0,0 to 1, 1 with if statement
            Edge randomWall = internalWalls[randomNum];
            Edge lastElement = internalWalls[internalWallsRemaining - 1];
            MazeCell room1;
            MazeCell room2;
            // Get the two nearby rooms
            if (randomWall.isVertical())
            {
                room1 =
                    new MazeCell(new Point(
                        randomWall.getX() - 1,
                        randomWall.getY() - 1));
                room1.setRoomNumber(mazeRoom[randomWall.getX() - 1][randomWall
                    .getY() - 1].getRoomNumber());
                room2 =
                    new MazeCell(new Point(
                        randomWall.getX() - 1,
                        randomWall.getY()));
                room2.setRoomNumber(mazeRoom[randomWall.getX() - 1][randomWall
                    .getY()].getRoomNumber());
            }
            else
            {
                room1 =
                    new MazeCell(new Point(
                        randomWall.getX() - 1,
                        randomWall.getY() - 1));
                room1.setRoomNumber(mazeRoom[randomWall.getX() - 1][randomWall
                    .getY() - 1].getRoomNumber());
                room2 =
                    new MazeCell(new Point(
                        randomWall.getX(),
                        randomWall.getY() - 1));
                room2.setRoomNumber(mazeRoom[randomWall.getX()][randomWall
                    .getY() - 1].getRoomNumber());
            }
            if (randomWall.isVertical())
            {
                mazeRoom[(int)room1.getCoordinates().getX()][(int)room1
                    .getCoordinates().getY()].setE(false);
                mazeRoom[(int)room2.getCoordinates().getX()][(int)room2
                    .getCoordinates().getY()].setW(false);
            }
            else
            {
                mazeRoom[(int)room1.getCoordinates().getX()][(int)room1
                    .getCoordinates().getY()].setS(false);
                mazeRoom[(int)room2.getCoordinates().getX()][(int)room2
                    .getCoordinates().getY()].setN(false);
            }

            internalWalls[internalWallsRemaining - 1] = randomWall;
            internalWalls[randomNum] = lastElement;
            internalWallsRemaining--;
        }
    }


    // ----------------------------------------------------------
    /**
     * Remove a random wall from the mazeRoom. Does not create a cycle.
     */
    public void removeWall()
    {

        // Remove a random wall
        int randomNum = (int)(Math.random() * this.getInternalWallsRemaining());
        // Fix boundary cases of 0,0 to 1, 1 with if statement
        Edge randomWall = internalWalls[randomNum];
        Edge lastElement = internalWalls[internalWallsRemaining - 1];
        MazeCell room1;
        MazeCell room2;
        // Get the two nearby rooms
        if (randomWall.isVertical())
        {
            room1 =
                new MazeCell(new Point(
                    randomWall.getX() - 1,
                    randomWall.getY() - 1));
            room1.setRoomNumber(mazeRoom[randomWall.getX() - 1][randomWall
                .getY() - 1].getRoomNumber());
            room2 =
                new MazeCell(
                    new Point(randomWall.getX() - 1, randomWall.getY()));
            room2.setRoomNumber(mazeRoom[randomWall.getX() - 1][randomWall
                .getY()].getRoomNumber());

        }
        else
        {
            room1 =
                new MazeCell(new Point(
                    randomWall.getX() - 1,
                    randomWall.getY() - 1));
            room1.setRoomNumber(mazeRoom[randomWall.getX() - 1][randomWall
                .getY() - 1].getRoomNumber());
            room2 =
                new MazeCell(
                    new Point(randomWall.getX(), randomWall.getY() - 1));
            room2
                .setRoomNumber(mazeRoom[randomWall.getX()][randomWall.getY() - 1]
                    .getRoomNumber());
        }

        // Remove this wall from the graph if rooms NOT in same set
        if (!(roomDS.inSameSet(room1.getRoomNumber(), room2.getRoomNumber()) && internalWallsRemaining > 0))
        {
            roomDS.union(room1.getRoomNumber(), room2.getRoomNumber());
            if (randomWall.isVertical())
            {
                mazeRoom[(int)room1.getCoordinates().getX()][(int)room1
                    .getCoordinates().getY()].setE(false);
                mazeRoom[(int)room2.getCoordinates().getX()][(int)room2
                    .getCoordinates().getY()].setW(false);
            }
            else
            {
                mazeRoom[(int)room1.getCoordinates().getX()][(int)room1
                    .getCoordinates().getY()].setS(false);
                mazeRoom[(int)room2.getCoordinates().getX()][(int)room2
                    .getCoordinates().getY()].setN(false);
            }

            internalWalls[internalWallsRemaining - 1] = randomWall;
            internalWalls[randomNum] = lastElement;
            internalWallsRemaining--;
        }
    }


    /**
     * Perform a breadth first search on mazeRoom
     *
     * @param room
     * @param ending
     * @return the path
     */
    public Queue<MazeCell> BFS(MazeCell room, MazeCell ending)
    {
        Queue<MazeCell> parent = new Queue<MazeCell>();
        Queue<MazeCell> q = new Queue<MazeCell>();

        Queue<MazeCell> BFSpath = new Queue<MazeCell>();
        q.enqueue(room);
        MazeCell adjacentRoom = room;
        MazeCell v;
        MazeCell p;
        BFSpath.enqueue(adjacentRoom);
        while (!q.isEmpty())
        {
            v = q.dequeue();
            if (v.equals(ending))
            {
                return BFSpath;
            }

            if (v.isN() == false)
            {
                adjacentRoom =
                    mazeRoom[(int)v.getCoordinates().getX() - 1][(int)v
                        .getCoordinates().getY()];
                p =
                    mazeRoom[(int)v.getCoordinates().getX()][(int)v
                        .getCoordinates().getY()];
                if (!adjacentRoom.isVisited())
                {
                    parent.enqueue(p);
                    adjacentRoom.setParent(p);
                    adjacentRoom.setVisited(true);
                    path.enqueue(room);
                    q.enqueue(adjacentRoom);
                }
            }

            if (v.isE() == false)
            {
                adjacentRoom =
                    mazeRoom[(int)v.getCoordinates().getX()][(int)v
                        .getCoordinates().getY() + 1];
                p =
                    mazeRoom[(int)v.getCoordinates().getX()][(int)v
                        .getCoordinates().getY()];
                if (!adjacentRoom.isVisited())
                {
                    adjacentRoom.setVisited(true);
                    parent.enqueue(p);
                    adjacentRoom.setParent(p);
                    BFSpath.enqueue(adjacentRoom);
                    q.enqueue(adjacentRoom);
                }
            }

            if (v.isS() == false)
            {
                adjacentRoom =
                    mazeRoom[(int)v.getCoordinates().getX() + 1][(int)v
                        .getCoordinates().getY()];
                p =
                    mazeRoom[(int)v.getCoordinates().getX()][(int)v
                        .getCoordinates().getY()];
                if (!adjacentRoom.isVisited())
                {
                    adjacentRoom.setParent(p);
                    adjacentRoom.setVisited(true);
                    parent.enqueue(p);
                    BFSpath.enqueue(adjacentRoom);
                    q.enqueue(adjacentRoom);
                }

            }

            if (v.isW() == false)
            {
                adjacentRoom =
                    mazeRoom[(int)v.getCoordinates().getX()][(int)v
                        .getCoordinates().getY() - 1];
                p =
                    mazeRoom[(int)v.getCoordinates().getX()][(int)v
                        .getCoordinates().getY()];
                if (!adjacentRoom.isVisited())
                {
                    adjacentRoom.setParent(p);
                    adjacentRoom.setVisited(true);
                    parent.enqueue(p);
                    path.enqueue(room);
                    q.enqueue(adjacentRoom);
                }
            }

        }
        return BFSpath;
    }


    /**
     * Perform a depth first search on the mazeRoom
     *
     * @param room
     * @param ending
     */
    public void DFS(MazeCell room, MazeCell ending)
    {
        room.setVisited(true);
        room.setOnPath(true);
        if (room.equals(ending))
        {
            System.out.print(this);
            System.out.println("Path Length = " + pathLength);
            pathsFound++;
            System.out.println();
            room.setVisited(false);
            room.setOnPath(false);
            return;
        }
        MazeCell adjacentRoom;

        if (room.isN() == false)
        {
            adjacentRoom =
                mazeRoom[(int)room.getCoordinates().getX() - 1][(int)room
                    .getCoordinates().getY()];
            if (!adjacentRoom.isVisited())
            {
                room.setMarkNorth(true);
                adjacentRoom.setMarkSouth(true);
                pathLength++;
                DFS(adjacentRoom, ending);
                pathLength--;
                room.setMarkNorth(false);
                adjacentRoom.setMarkSouth(false);
            }
        }
        if (room.isE() == false)
        {

            adjacentRoom =
                mazeRoom[(int)room.getCoordinates().getX()][(int)room
                    .getCoordinates().getY() + 1];
            if (!adjacentRoom.isVisited())
            {
                room.setMarkEast(true);
                adjacentRoom.setMarkWest(true);
                pathLength++;
                DFS(adjacentRoom, ending);
                pathLength--;
                room.setMarkEast(false);
                adjacentRoom.setMarkWest(false);
            }
        }
        if (room.isS() == false)
        {
            adjacentRoom =
                mazeRoom[(int)room.getCoordinates().getX() + 1][(int)room
                    .getCoordinates().getY()];
            if (!adjacentRoom.isVisited())
            {
                room.setMarkSouth(true);
                adjacentRoom.setMarkNorth(true);
                pathLength++;
                DFS(adjacentRoom, ending);
                pathLength--;
                room.setMarkSouth(false);
                adjacentRoom.setMarkNorth(false);
            }
        }
        if (room.isW() == false)
        {
            adjacentRoom =
                mazeRoom[(int)room.getCoordinates().getX()][(int)room
                    .getCoordinates().getY() - 1];
            if (!adjacentRoom.isVisited())
            {
                room.setMarkWest(true);
                adjacentRoom.setMarkEast(true);
                pathLength++;
                DFS(adjacentRoom, ending);
                pathLength--;
                room.setMarkWest(false);
                adjacentRoom.setMarkEast(false);
            }
        }
        room.setVisited(false);
        room.setOnPath(false);
        return;
    }


    public String toString()
    {
        String info = "";
        for (int x = 0; x < rows; x++)
        {
            if (x == 0)
            {
                info = info + "+ ";
            }
            else
            {
                info = info + "+-";
            }
        }
        info = info + "+\n";

        for (int i = 0; i < mazeRoom.length; i++)
        {
            for (int j = 0; j < mazeRoom[i].length; j++)
            {

                if (mazeRoom[i][j].isW())
                {
                    if (mazeRoom[i][j].isOnPath())
                    {

                        info = info + "|#";

                    }
                    else
                    {
                        info = info + "| ";
                    }
                }
                else
                {
                    if (mazeRoom[i][j].isOnPath())
                    {
                        if (mazeRoom[i][j].isMarkWest())
                        {
                            info = info + "##";
                        }
                        else
                        {
                            info = info + " #";
                        }

                    }
                    else
                    {

                        info = info + "  ";
                    }

                }
            }

            info = info + "|\n";
            for (int j = 0; j < mazeRoom[i].length; j++)
            {
                if (mazeRoom[i][j].isS())
                {
                    if (mazeRoom[i][j].getRoomNumber() == rooms - 1)
                    {
                        info = info + "+ ";
                    }
                    else
                    {
                        info = info + "+-";
                    }
                }
                else
                {
                    if (mazeRoom[i][j].isMarkSouth())
                    {
                        info = info + "+#";
                    }
                    else
                    {
                        info = info + "+ ";
                    }
                }

            }
            info = info + "+\n";

        }

        return info;
    }


    // ----------------------------------------------------------
    /**
     * @param columns
     *            the columns to set
     */
    public void setColumns(int columns)
    {
        this.columns = columns;
    }


    // ----------------------------------------------------------
    /**
     * @return the columns
     */
    public int getColumns()
    {
        return columns;
    }


    // ----------------------------------------------------------
    /**
     * @param mazeRoom
     *            room
     */
    public void getMazeRooms(MazeCell[][] mazeRoom)
    {
        this.mazeRoom = mazeRoom;
    }


    // ----------------------------------------------------------
    /**
     * @return the columns
     */
    public MazeCell[][] getMazeRooms()
    {
        return mazeRoom;
    }


    // ----------------------------------------------------------
    /**
     * @param rows
     *            the rows to set
     */
    public void setRows(int rows)
    {
        this.rows = rows;
    }


    // ----------------------------------------------------------
    /**
     * @return the rows
     */
    public int getRows()
    {
        return rows;
    }


    // ----------------------------------------------------------
    /**
     * @param totalWalls
     */
    public void setWalls(int totalWalls)
    {
        this.totalWalls = totalWalls;
    }


    // ----------------------------------------------------------
    /**
     * @return the walls
     */
    public int getWalls()
    {
        return totalWalls;
    }


    // ----------------------------------------------------------
    /**
     */
    public void setRooms()
    {
        this.rooms = columns * rows;
    }


    // ----------------------------------------------------------
    /**
     * @return the rooms
     */
    public int getRooms()
    {
        return rooms;
    }


    /**
     * @param internalWallsRemaining
     */
    public void setInternalWallsRemaining(int internalWallsRemaining)
    {
        this.internalWallsRemaining = internalWallsRemaining;
    }


    /**
     * @return number internal walls remaining
     */
    public int getInternalWallsRemaining()
    {
        return internalWallsRemaining;
    }


    /**
     * @param internalWalls
     */
    public void setInternalWalls(Edge[] internalWalls)
    {
        this.internalWalls = internalWalls;
    }


    /**
     * @return array of internal edges
     */
    public Edge[] getInternalWalls()
    {
        return internalWalls;
    }


    /**
     * @param pathsFound
     */
    public void setPathsFound(int pathsFound)
    {
        this.pathsFound = pathsFound;
    }


    /**
     * @return the paths
     */
    public int getPathsFound()
    {
        return pathsFound;
    }

}
