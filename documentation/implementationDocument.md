# Implementation


## Program description

This program is used to compare three path finding algorithms - Dijkstra, A star and JPS. 
The program takes movinai map files and user text as input. The program prints various results comparing the algorithms as output.


## Package and class description

The program is divided into three packages.

[pathFinders](https://github.com/synesteesia/pathAlgorithms/tree/master/pathAlgorithms/src/main/java/pathalgorithms/pathFinders)

Contains the code for Dijkstras, A star and JPS algorithm.

[dataStructures](https://github.com/synesteesia/pathAlgorithms/tree/master/pathAlgorithms/src/main/java/pathalgorithms/dataStructures)

Contains simple implementations of ArrayList, MinHeap, Graph and Vertex.

[IO](https://github.com/synesteesia/pathAlgorithms/tree/master/pathAlgorithms/src/main/java/pathalgorithms/IO)

Contains the user text interface, parser for map files and PerformanceStats class to calculate some of the results.

The UI class is the class taking all the direct input and sending it to other classes.
The parser class is used to process .map files into a grid data structures, 
so they can be ran through the pathfinding algorithms.
While running the pathfinding algorithms, some data is stored and then used by PerformanceStats class to calculate some of the results.
Each algorithm prints their results through the UI class.


## Implemented algorithms

### Dijkstra's

Dijkstra's algorithm is an algorithm for finding the shortest path between two points in a weighted graph. The algorithm will find all the shortest paths between the starting point and all other points unless the algorithm is stoped early at the desired point.

Dijkstra's algorithm uses a priority queue to first process the paths with the least weight.

Dijkstra's algorithm is a natural choice for a pathfinding program, the algorithm will always find a correct path, it is efficent enough to be used on large graphs and it is relatively easy to implement.

### A Star

A Star search algorithm is an algorithm for finding the shortest path between two points in a weighted graph. A Star search algorithm can be seen as an extention of Dijktra's algorithm. It achieves better performance by using heuristics to guide its search.

A Star search algorithm uses a priority queue with heuristics to help reduce the amount of nodes it has to process.

A Star search algorithm is a natural choice for a pathfinding program because it is similar to Dijktra's algorithm but uses heuristics to increase its efficency.

### Jump point search

Jump point search algorithm (JPS) is an algorithm for finding the shortest path between two points in an uniform-cost grid. JPS is an extention to the A Star search algorithm. It achieves better performance by pruning certain unfavorable nodes before they are processed and creating 'jump points' along straight lines on the grid.

JPS is a natural choice for shortest pathfinding in an uniform-cost grid because it can greatly improve the efficency over A Star algorithm.


## Inputs

The program takes one or multiple maps and user text as input. If the user wants to run the algorithms for a single map, start and goal coordinates are typed in by the user. Otherwise the program selects movable verices in the upper left corner as start and right down corner as goal.

## Map files

The program reads .map files that can be found on site:
https://www.movingai.com/benchmarks/grids.html 

In the maps a dot represents acceptable directions for movement, other letters are walls.

## Expected time and space complexities of the algorithms

|E| is the number of edges and |V| is the number of vertices.

|Algorithm| Time Complexity| Space Complexity|
|---------|----------------|-----------------|
|Dijkstra| O\(\(\|E\|+\|V\|\)log\|V\|\) | O\(\|V\|\) | 
|A Star| O\(\|E\|\) | O\(\|V\|\) |
|JPS| O\(\|E\|\) | O\(\|V\|\) |


## Output

The program prints questions to the user and results about different algorithms.
More about results algorithm performance tests can be found on the test document.

## User interface

The project uses a simple text interface and can be found in its own Ui class.
More details about the text interface can be found on the instructions document.


## Sources

