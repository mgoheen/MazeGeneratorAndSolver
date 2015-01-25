// -------------------------------------------------------------------------
/**
 * Generates a maze using unions. Solves the maze using both a breadth first
 * (shortest path) and depth first search (all paths).
 *
 * @author Michael Goheen
 * @version Apr 10, 2012
 */
public class Maze
{
    // ----------------------------------------------------------
    /**
     * Main method to generate and solve mazes. If no parameters are given, it
     * will generate a default maze of 40 columns, 40 rows, and 10 walls.
     *
     * @param args
     *            0 or 3 arguments only.
     */
    public static void main(String args[])
    {
        TheMaze maze;
        Queue<MazeCell> BFSpath = new Queue<MazeCell>();
        int c = 0, r = 0, w = 0;
        if (args.length == 3)
        {
            try
            {
                c = Integer.parseInt(args[0]);
                r = Integer.parseInt(args[1]);
                w = Integer.parseInt(args[2]);
                if (c > 0 && r > 0)
                {
                    // Generate maze with no cycles
                    maze = new TheMaze(c, r);
                    System.out.println("Maze " + c + " cols x " + r + " rows:");
                    System.out.println(maze);
                    // Modify maze to remove w walls
                    for (int i = 0; i < w; i++)
                    {
                        maze.removeWallForCycles();
                    }
                    System.out
                        .println("Modified Maze removed " + w + " walls:");
                    System.out.println(maze);

                    // BFS
                    System.out.println("BFS:");
                    BFSpath =
                        maze.BFS(
                            maze.getMazeRooms()[0][0],
                            maze.getMazeRooms()[c - 1][r - 1]);

                    Queue<MazeCell> correctBFSpath = new Queue<MazeCell>();
                    MazeCell current = BFSpath.rear();

                    while (current != maze.getMazeRooms()[0][0])
                    {
                        correctBFSpath.enqueue(current);
                        current = current.getParent();
                    }
                    correctBFSpath.enqueue(current);
                    int x, y;
                    int pathlength = correctBFSpath.size();
                    MazeCell currentRoom;
                    for (int i = 0; i < pathlength; i++)
                    {
                        x = (int)correctBFSpath.peek().getCoordinates().getX();
                        y = (int)correctBFSpath.peek().getCoordinates().getY();
                        correctBFSpath.dequeue();
                        currentRoom = maze.getMazeRooms()[x][y];
                        currentRoom.setOnPath(true);

                    }
                    System.out.print(maze);
                    System.out.println("Path length = " + pathlength);

                    // Reinitialize cells
                    for (int i = 0; i < maze.getMazeRooms().length; i++)
                    {
                        for (int j = 0; j < maze.getMazeRooms()[i].length; j++)
                        {
                            maze.getMazeRooms()[i][j].setVisited(false);
                            maze.getMazeRooms()[i][j].setOnPath(false);
                        }
                    }

                    // DFS
                    System.out.println("\nDFS:");
                    maze.DFS(
                        maze.getMazeRooms()[0][0],
                        maze.getMazeRooms()[c - 1][r - 1]);
                    System.out.println(maze.getPathsFound() + " paths found.");
                }
                else
                {
                    System.out
                        .println("ERROR! Columns and rows must be greater than 0.");
                }
            }

            catch (Exception e)
            {
                System.out
                    .println("ERROR! Make sure the inputs are integer numbers: "
                        + e.getLocalizedMessage());
            }

        }
        else if (args.length == 0)
        {
            c = 40;
            r = 40;
            w = 10;
            if (c > 0 && r > 0)
            {
                // Generate maze with no cycles
                maze = new TheMaze(c, r);
                System.out.println("Maze " + c + " cols x " + r + " rows:");
                System.out.println(maze);
                // Modify maze to remove w walls
                for (int i = 0; i < w; i++)
                {
                    maze.removeWallForCycles();
                }
                System.out.println("Modified Maze removed " + w + " walls:");
                System.out.println(maze);

                // BFS
                System.out.println("BFS:");
                BFSpath =
                    maze.BFS(
                        maze.getMazeRooms()[0][0],
                        maze.getMazeRooms()[c - 1][r - 1]);

                Queue<MazeCell> correctBFSpath = new Queue<MazeCell>();
                MazeCell current = BFSpath.rear();

                while (current != maze.getMazeRooms()[0][0])
                {
                    correctBFSpath.enqueue(current);
                    current = current.getParent();
                }
                correctBFSpath.enqueue(current);
                int x, y;
                int pathlength = correctBFSpath.size();
                MazeCell currentRoom;
                for (int i = 0; i < pathlength; i++)
                {
                    x = (int)correctBFSpath.peek().getCoordinates().getX();
                    y = (int)correctBFSpath.peek().getCoordinates().getY();
                    correctBFSpath.dequeue();
                    currentRoom = maze.getMazeRooms()[x][y];
                    currentRoom.setOnPath(true);

                }
                System.out.print(maze);
                System.out.println("Path length = " + pathlength);

                // Reinitialize cells
                for (int i = 0; i < maze.getMazeRooms().length; i++)
                {
                    for (int j = 0; j < maze.getMazeRooms()[i].length; j++)
                    {
                        maze.getMazeRooms()[i][j].setVisited(false);
                        maze.getMazeRooms()[i][j].setOnPath(false);
                    }
                }

                // DFS
                System.out.println("\nDFS:");
                maze.DFS(
                    maze.getMazeRooms()[0][0],
                    maze.getMazeRooms()[c - 1][r - 1]);
                System.out.println(maze.getPathsFound() + " paths found.");
            }
            else
            {
                System.out.println("ERROR! Incorrect number of arguments.");
            }
        }
    }
}
