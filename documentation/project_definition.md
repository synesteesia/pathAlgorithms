  # Project definition
  
This project compares different pathfinding algorithms with Movin ai labs 2d maps.
The aim is to learn in which cases each algorithm is the best option 
by giving the time taken by each algorithm as output.

### Algorithms and data structrures

|| Dijkstra’s algorithm | A* | JPS
|------|----------|----------|----------
| Data structures | Arrays, Fibonacci heap | Arrays, queue | Arrays, sets, maps, queue 
| Time complexity (worst case) | O(V log(V) + E) | O(V log(V) + E) | O(V log(V) + E) 

I chose the algorithms purely based on personal curiosity, A* and JPS are both new to me.


### Input

The program is given text files from Movin ai Labs 2d site.
In the maps a dot represents acceptable directions for movement, T letters are walls.
The user can give the program start and destination coordinates as input.


### Sources

* http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
* https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
* https://www.geeksforgeeks.org/a-search-algorithm/
* http://users.cecs.anu.edu.au/~dharabor/data/papers/harabor-grastien-aaai11.pdf
* https://movingai.com/benchmarks/grids.html
