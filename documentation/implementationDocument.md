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

Dijkstra's algorithm is perhaps the most known path finding algorithm. 
It finds the shortest path between all vertices in a weighted graph, 
unless it is stopped when finding a specified vertex (like in this program).

The algorithm can be found in the following class in the method run():
[Dijkstra](https://github.com/synesteesia/pathAlgorithms/blob/master/pathAlgorithms/src/main/java/pathalgorithms/pathFinders/Dijkstra.java).

The algorithm uses the data structure heap, and maintaining it is the most computationally complex part of the algorithm.

A star and JPS are heavily based on Dijkstra's algorithm.

### A Star

A star algorithm finds the shortest path between two points in a weighted graph. In comparison do Dijkstra's, A star uses a priority queue with heuristics to reduce the amount of nodes it has to process. Better performance is achieved with heuristics guiding the search. Otherwise the algorithm is identical with Dijkstra's algorithm.

The algorithm can be found in the following class in the method run():
[A star](https://github.com/synesteesia/pathAlgorithms/blob/master/pathAlgorithms/src/main/java/pathalgorithms/pathFinders/AStar.java).

The heuristic used in this project is simple euclidean and cn be foundin the method  euclideanDistance(int vertex).


### Jump point search

The algorithm can be found in the following class in the method run():
[JPS](https://github.com/synesteesia/pathAlgorithms/blob/master/pathAlgorithms/src/main/java/pathalgorithms/pathFinders/JPS.java).

Jump Point Search hyödyntää solmuja laajentaessaan niin kutsuttuja karsintasääntö-jä (pruning rules). Karsintasääntöjen avulla algoritmi voi sivuuttaa sellaiset tarkasteltavansolmun naapurit, joihin on olemassa lyhempi tai yhtä lyhyt polku, joka ei kulje tarkas-teltavan solmun läpi. Käyttämällä näitä sääntöjä rekursiivisesti jokaiseen solmun naapu-riin jota ei poissuljettu karsinnassa, algoritmi löytää niin kutsuttujahyppypisteitä(jumppoint); solmuja, jotka ovat välttämätön osa lyhintä reittiä yhteen tai useampaan naapu-riinsa. Keskittymällä näihin solmuihin algoritmi karsii pois symmetrisiä polkuja.

Harabor ja Grastien todistavat, että optimaalinen lyhin polku on mahdollista löytäälaajentamalla vain hyppypistesolmuja. Tämä vähentää merkittävästi laajennettujen sol-mujen määrää, joka poistaa A*:lle tyypillisen prioriteettijonosta muodostuvan pullonkau-lan.

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
More about results and algorithm performance tests can be found on the test document.

## User interface

The project uses a simple text interface and can be found in its own Ui class.
More details about the text interface can be found on the instructions document.


## Sources

* http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
* https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
* https://www.geeksforgeeks.org/a-search-algorithm/
* http://users.cecs.anu.edu.au/~dharabor/data/papers/harabor-grastien-aaai11.pdf
* https://movingai.com/benchmarks/grids.html
* https://harablog.wordpress.com/2011/09/07/jump-point-search/
* http://runedegroot.com/jump-point-search-jps-pathfinding-algorithm/
* https://www.gamedev.net/tutorials/programming/artificial-intelligence/jump-point-search-fast-a-pathfinding-for-uniform-cost-grids-r4220/
* https://trepo.tuni.fi/bitstream/handle/10024/121424/PekkaOinas.pdf?sequence=2&isAllowed=y

