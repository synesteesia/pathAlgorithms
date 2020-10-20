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
Average and median performance times are calculated from remaining results. 
Additionally the operating systems task scheduling and os overhead might impact the results.

To see how the algorithms scale with input size, performance testing is done with increasing number of maps as input.
To get better results, a bigger number of maps or more dynamic implemention could be used.

Each pathfinding algorithm is divided into two parts. 
First, the class compiles the datastructures the algorithm needs to function and after this the pathfinding is run.
Time is measured for both parts, data structure initialization and run time.
`System.nanotime` is used for timing. 
Taking a timestamp as close to the code-to-time as possible and comparing the timestamp after the intresting code has run.
As mentioned before, both parts of the algorithm are ran 1000 times per input map.



### Performance testing path algorithms

Performance tests for the two RMQ structures are written in `Tester.java`.

In testing two different values are of intrest for data structure comparison. The preprocessing time taken when building the different data structures and the time taken when querying the data structures. Additionally it may be intresting to get timing for updating the dynamic structures but this is skipped since we have no point of comparison.


### Data structure initialization time

For testing the preprocessing time, both of the structures are built 100 times for each of the input array sizes, and the median is stored. Here the median is used instead of the mean to avoid the extreme outlier of JIT compilation and to minimize the impact of garbage collection.

```java
        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            runPreprocessing(graph);
            stop = System.nanoTime();
            preprocessing.setValue(i, stop - start);
        }

        preprocessing.computeStats();
```

`System.nanotime` is used for timing. Taking a timestamp as close to the code-to-time as possible and comparing the timestamp after the intresting code has run.

### Run time

To test query times, 10000 queries are generated for each of the structures. Each of these queries are run 100 times and the mean is taken for each query time. After the queries have run, the mean and standard deviation of each of the queries are reported.

```java
        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            run();
            stop = System.nanoTime();
            runTime.setValue(i, stop - start);
        }

        runTime.computeStats();
```

The multiple level repetition may seem slightly excessive but reducing the number of iteration on the inner loop tends to make results fairly noisy. The inner level averaging is simply there to reduce impact of garbage collection and operating system overhead. If running on a computer with more capacity to spare, fewer iterations may be required.

The timing itself was done exactly the same as for the preprocessing times. Times as close to before and after executing the `query(l, r)` are compared for a "real world" execution time.


### Example results

Tests were run on an `Intel(R) Core(TM) i5-3437U` cpu with 8GB of RAM. The computer was fairly old and under fairly heavy load during testing. As such the results here are likely more noisy than if run on a system with more resources.

```
Dynamic RMQ preprocessng times:
      10: 0.014798ms
     100: 0.010918ms
    1000: 0.018245ms
   10000: 0.037604ms
  100000: 0.319337ms
 1000000: 3.934933ms
10000000: 62.961225ms

Static RMQ preprocessing times:
      10: 0.022808ms
     100: 0.02369ms
    1000: 0.298455ms
   10000: 4.356129ms
  100000: 52.450047ms
 1000000: 647.011838ms
10000000: 7818.361586ms
```

The results for preprocessing times are not unexpected. The segment tree used in the dynamic implementation is built using a very efficient linear algorithm where almost all the data fits into the CPU caches for all but the largest array, while the preprocessing for the static RMQ runs in <sub><img src="https://latex.codecogs.com/svg.latex?\mathcal{O}(n&space;\log&space;n)" title="O(n log n)" /></sub> time and runs out of CPU cache space sooner.

```
Dynamic RMQ lookup times:
      10: 46.5028ns, std: 28.960608830727722ns
     100: 52.1385ns, std: 14.596678528087555ns
    1000: 57.8515ns, std: 18.018393768629977ns
   10000: 77.0664ns, std: 47.23534173466832ns
  100000: 87.4741ns, std: 21.990904692142944ns
 1000000: 113.0819ns, std: 36.47509061830483ns
10000000: 124.5604ns, std: 31.629694838862616ns

Static RMQ lookup times:
      10: 93.1286ns, std: 25.14617456094359ns
     100: 113.1642ns, std: 22.816167428158053ns
    1000: 99.168ns, std: 13.6808074487305ns
   10000: 107.9099ns, std: 36.98128639655819ns
  100000: 99.3353ns, std: 14.551111551862904ns
 1000000: 101.7498ns, std: 15.629012334842363ns
10000000: 107.4176ns, std: 23.026359494352093ns
```

For the lookup time the results are perhaps more intresting but significantly more noisy. 

Clearly the dynamic structure is faster for small array sizes. This is likely due to the very good cache performance and low constant factors of the structure. 

The static structure seems to stay fairly consistent at somewhere around 100ns per query. This can likely be expalined by the very high constant factors of calculating base 2 logarithms in java and poor cache performanse. 

It is intresting to note tho that the static structure is faster and with a lower standard deviation for arrays of sizes 1000000 and 10000000. The expectation would be that this difference would be more pronounced as array sizes grow further.



Mitä on testattu, miten tämä tehtiin
Minkälaisilla syötteillä testaus tehtiin (vertailupainotteisissa töissä tärkeää)
Miten testit voidaan toistaa
Ohjelman toiminnan empiirisen testauksen tulosten esittäminen graafisessa muodossa.

