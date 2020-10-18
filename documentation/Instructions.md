# Instructions


### generating an executable jar

command

```
mvn package
```

generates a jar file to _target_ folder.


### running the jar file

command

```
java -jar pathAlgorithms.jar
```

### Input

This program reads.map files to print data on different path algorithms.
Accepted map files can be found on movinai site:
https://www.movingai.com/benchmarks/grids.html
It is possible to give the program a single map or a set of maps as input.

### Text interface

The progran works with a text interface, asking user questions.
The first questin is:

"Would you like to process single map or a set of maps?"
Answer by typing "single" or "set" and hitting enter.

### Running tests on a single map

### Running tests on a set of maps

The first question is where the program can find a map file:

"Write the absoulte path to the .map file on the next line:""



Next the user needs to type in the starting and destination coordinates.
ie.

"Give X coordinate of start vertex

147

Give Y coordinate of start vertex

75

Give X coordinate of goal vertex

290

Give Y coordinate of goal vertex

197
"

After giving the last coordinate value the program runs first the Dijkstra algorithm and then AStar, printing results.

Finally the user can run more tests by answering the question:

"would you like to run another test? y/n"
