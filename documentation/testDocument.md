# Testdocument

The program has been tested using JUnit tests and manual testing. 
Perfomance testing is part of the core functionality of this program.
Since the program works with a text interface correct pathfinding has been tested manually.

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

For testing the data structure initialization time, the data structures are built 1000 times for each of the maps, and the timestamps are stored. runPreprocessing(graph); contains the method for data structure initialization.

```java
        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            runPreprocessing(graph);
            stop = System.nanoTime();
            preprocessing.addValue(stop - start);
        }
```

### Run time

To test run times, the pathfinding algorithm is runs 1000 times and timestamps are stored. run(); method contains the pathfinding algorithm.

```java
        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            run();
            stop = System.nanoTime();
            runTime.addValue(stop - start);
        }
```


### Example results

Tests were run on a computer with `Intel® Core™ i7-6500U` cpu and 8GB of RAM. The computer was by no means ideal for performance testing.
The maps used can be found on the site: https://movingai.com/benchmarks/grids.html by clicking link "donwload all maps". 
Game maps are added to each set - set1 contains Dragon Age maps. set2 contains Dragon Age AND Dragon Age 2 etc.
The sets are included in the project folder [PerformanceTests](https://github.com/synesteesia/pathAlgorithms/tree/master/pathAlgorithms/PerformanceTests).

set1: Dragon Age: Origins, 156 maps total.

set2: added Dragon Age 2, 223 maps total.

set3: added Warcraft 3, 259 maps total.

set4: added Baldurs Gate 2, 334 maps total.

set5: added Starcraft, 409 maps total.

For further testing more sets could downloaded or created.


### Data structure initialization

```
Set 1 results:
Dijkstra preprocessing
	Average: 108876.35298944224(ns), 1.0887635298944223E-4(s)
	Standard deviation: 1551450.1260699607
	Minimum and maximum: 238, 436085956

A* preprocessing
	Average: 209341.52694568556(ns), 2.0934152694568556E-4(s)
	Standard deviation: 486110.1693762965
	Minimum and maximum: 319, 21344784

JPS preprocessing
	Average: 306667.32924570027(ns), 3.0666732924570025E-4(s)
	Standard deviation: 884988.8515450774
	Minimum and maximum: 507, 224624869
```
```
Set 2 results:
Dijkstra preprocessing
	Average: 110362.90007578509(ns), 1.1036290007578509E-4(s)
	Standard deviation: 2026918.834152365
	Minimum and maximum: 217, 575062112

A* preprocessing
	Average: 202381.6229938251(ns), 2.0238162299382508E-4(s)
	Standard deviation: 523007.7308631049
	Minimum and maximum: 305, 140131798

JPS preprocessing
	Average: 295717.9096543034(ns), 2.957179096543034E-4(s)
	Standard deviation: 593444.050604185
	Minimum and maximum: 560, 17762447
```
```
Set 3 results:
Dijkstra preprocessing
	Average: 113226.51885914618(ns), 1.1322651885914618E-4(s)
	Standard deviation: 948429.3285139131
	Minimum and maximum: 224, 241873370

A* preprocessing
	Average: 210374.21949119493(ns), 2.1037421949119493E-4(s)
	Standard deviation: 465510.02049124124
	Minimum and maximum: 299, 121131211

JPS preprocessing
	Average: 300602.4987548214(ns), 3.006024987548214E-4(s)
	Standard deviation: 1140826.6021560186
	Minimum and maximum: 590, 513106580
```
```
Set 4 results:
Dijkstra preprocessing
	Average: 134824.81123296777(ns), 1.3482481123296776E-4(s)
	Standard deviation: 1702787.7005887406
	Minimum and maximum: 227, 462959147

A* preprocessing
	Average: 233096.11873688243(ns), 2.3309611873688243E-4(s)
	Standard deviation: 776591.3630422602
	Minimum and maximum: 325, 397176580

JPS preprocessing
	Average: 328805.93262554676(ns), 3.2880593262554677E-4(s)
	Standard deviation: 713918.304338002
	Minimum and maximum: 547, 270413193
```
```
Set 5 results:
Dijkstra preprocessing
	Average: 173184.0489609999(ns), 1.7318404896099991E-4(s)
	Standard deviation: 2261734.834848309
	Minimum and maximum: 208, 491771293

A* preprocessing
	Average: 315873.43951696705(ns), 3.1587343951696703E-4(s)
	Standard deviation: 548325.430607372
	Minimum and maximum: 319, 140080716

JPS preprocessing
	Average: 459337.4770402862(ns), 4.5933747704028616E-4(s)
	Standard deviation: 938841.9530161287
	Minimum and maximum: 509, 386814816
```

### Run time

```
Set 1 results:
Dijkstra run time
	Average: 58630.353354829196(ns), 5.8630353354829195E-5(s)
	Standard deviation: 477510.66811565147
	Minimum and maximum: 90, 166935436

A* run time
	Average: 57308.48734927788(ns), 5.730848734927788E-5(s)
	Standard deviation: 672125.2417817245
	Minimum and maximum: 86, 256524633

JPS run time
	Average: 62460.978724222594(ns), 6.246097872422259E-5(s)
	Standard deviation: 987756.6408275339
	Minimum and maximum: 88, 337089525
```
```
Set 2 results:

Dijkstra run time
	Average: 58414.14552980058(ns), 5.841414552980058E-5(s)
	Standard deviation: 181256.08104225516
	Minimum and maximum: 87, 19760155

A* run time
	Average: 58228.85584688721(ns), 5.822885584688721E-5(s)
	Standard deviation: 589890.9812172188
	Minimum and maximum: 88, 269467030

JPS run time
	Average: 56928.33553065261(ns), 5.692833553065261E-5(s)
	Standard deviation: 877554.5918006276
	Minimum and maximum: 89, 410140731
```
```
Set 3 results:

Dijkstra run time
	Average: 63625.932215954504(ns), 6.362593221595451E-5(s)
	Standard deviation: 390111.971564684
	Minimum and maximum: 92, 133129093

A* run time
	Average: 61236.52738813663(ns), 6.123652738813663E-5(s)
	Standard deviation: 167509.62492126087
	Minimum and maximum: 91, 16861909

JPS run time
	Average: 61231.478492194954(ns), 6.123147849219496E-5(s)
	Standard deviation: 736717.6094416286
	Minimum and maximum: 101, 368433834
```
```
Set 4 results:

Dijkstra run time
	Average: 73962.31829736017(ns), 7.396231829736017E-5(s)
	Standard deviation: 356246.33808704116
	Minimum and maximum: 93, 122935864

A* run time
	Average: 71648.70411887461(ns), 7.16487041188746E-5(s)
	Standard deviation: 314533.59024146345
	Minimum and maximum: 94, 101706766

JPS run time
	Average: 70384.01094015251(ns), 7.03840109401525E-5(s)
	Standard deviation: 219292.812767416
	Minimum and maximum: 96, 100191581
```
```
Set 5 results:

Dijkstra run time
	Average: 98095.7237988357(ns), 9.809572379883569E-5(s)
	Standard deviation: 663372.7723426684
	Minimum and maximum: 95, 151605081

A* run time
	Average: 95064.1075137103(ns), 9.50641075137103E-5(s)
	Standard deviation: 675322.3781527523
	Minimum and maximum: 81, 269908664

JPS run time
	Average: 89738.98647184957(ns), 8.973898647184956E-5(s)
	Standard deviation: 322023.6139120842
	Minimum and maximum: 94, 153900395
```

One porrible reason JPS does not perform as well as it should compared to the other two algorithms in all of the sets might be because the graphs in the first first sets are smaller than the later ones. Thus, in small graphs the computational overhead of finding the cells to jump to overshadows the gains from skipping the rest of the cells.
