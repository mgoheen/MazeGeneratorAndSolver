# MazeGeneratorAndSolver
Generates random mazes and solves them.

First, a random maze is created with dimensions representing its borders. The maze is outputted as a series of ASCII text characters. (+) represents a corner. (-) and (|) represents walls or borders. Empty space ( ) represents available paths through the maze. After the maze is generated, more random walls are removed to create more available paths. The program will then solve each maze using a Breadth-first search (BFS) and Depth-first search (DFS). The BFS finds only one path which is also the shortest path. The DFS finds all available paths with the total number of paths found displayed at the very bottom. The paths are marked out clearly with the (#) character. The path length is given below each solution.
