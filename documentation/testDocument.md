# Testdocument

The program has been tested using JUnit tests and manual testing. 
Perfomance testing is part of the core functionality of this program.

### Test classes

All test classes can be found from the test folder:

[Test classes folder](https://github.com/synesteesia/pathAlgorithms/tree/master/pathAlgorithms/src/test/java).


### Test coverage


JUnit tests are run with command

```
mvn test
```
Testing report is created with command

```
mvn jacoco:report
```

Test report can be read by opening the file _target/site/jacoco/index.html_ with browser.

Current test coverage is 85%, this includes some redundant code like the UI tho.

## Performance testing

Since java is a JIT compiled language the performance of each algorithm is measured 1000 times per map, and the first result is discarded.
Average, median, min and max performance times are calculated from remaining results. 
Additionally the operating systems task scheduling and os overhead might impact the results.

To see how the algorithms scale with input size, performance testing is done with increasing number of maps as input.
To get better results, a bigger number of maps or more dynamic implemention could be used.

Each pathfinding algorithm is divided into two parts. 
First, the class compiles the datastructures the algorithm needs to function and after this the pathfinding is run.
Time is measured for both parts, data structure initialization and run time.
`System.nanotime` is used for timing. 
Taking a timestamp as close to the code-to-time as possible and comparing the timestamp after the intresting code has run.

As mentioned before, both parts of the algorithm are ran 1000 times per input map.
On each iteration the timestamp is stored and after the loop the class 
[PerformanceStats](https://github.com/synesteesia/pathAlgorithms/blob/master/pathAlgorithms/src/main/java/pathalgorithms/PerformanceStats.java) calculates the average, median, min and max time.


### Data structure initialization time

For testing the data structure initialization time, the data structures are built 1000 times for each of the maps, and the timestamps are stored. Afterwards the result times are calculated. runPreprocessing(graph); contains the method for data structure initialization.

```java
        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            runPreprocessing(graph);
            stop = System.nanoTime();
            preprocessing.setValue(i, stop - start);
        }

        preprocessing.computeStats();
```

### Run time

To test run times, the pathfinding algorithm is runs 1000 times and timestamps are stored. Afterwards the result times are calculated. run(); method contains the pathfinding algorithm.

```java
        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            run();
            stop = System.nanoTime();
            runTime.setValue(i, stop - start);
        }

        runTime.computeStats();
```


### Example results

Tests were run on a computer with `Intel® Core™ i7-6500U` cpu and 8GB of RAM. The computer was by no means ideal for performance testing.
The maps used can be found on the site: https://movingai.com/benchmarks/grids.html by clicking link "donwload all maps". 
Each different game is considered one set.

set1: Dragon Age: Origins

set2: Dragon Age 2

set3: Warcrft 3

set4: Baldurs Gate 2

set5: Starcraft

Each set contains approximately 

```
Dijkstra data structure init results:
      1 set: 0.014798ms
     2 sets: 0.010918ms
    3 sets : 0.018245ms
   4 sets  : 0.037604ms
  all sets : 0.319337ms
```
```
Astar data structure init results:
      1 set: 0.014798ms
     2 sets: 0.010918ms
    3 sets : 0.018245ms
   4 sets  : 0.037604ms
  all sets : 0.319337ms
```
```
JPS data structure init results:
      1 set: 0.014798ms
     2 sets: 0.010918ms
    3 sets : 0.018245ms
   4 sets  : 0.037604ms
  all sets : 0.319337ms
```

```
Dijkstra run results:
      1 set: 0.014798ms
     2 sets: 0.010918ms
    3 sets : 0.018245ms
   4 sets  : 0.037604ms
  all sets : 0.319337ms
```
```
A star run results:
      1 set: 0.014798ms
     2 sets: 0.010918ms
    3 sets : 0.018245ms
   4 sets  : 0.037604ms
  all sets : 0.319337ms
```
```
JPS run results:
      1 set: 0.014798ms
     2 sets: 0.010918ms
    3 sets : 0.018245ms
   4 sets  : 0.037604ms
  all sets : 0.319337ms
```

