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

The program works with a text interface, asking user questions.
The first question is:

"Would you like to process single map or a set of maps?"
Answer by typing "single" or "set" and hitting enter.

### Running tests on a single map

If the user answers "single" on the first question, 
next the program asks user for the absolute path of the map file.
Answer by typing ie. "/home/yourname/Downloads/blastedlands.map" and hitting enter.

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

After giving the last coordinate value the program runs first the each algorith, printing results.
Finally the user can run more tests by answering the question:
"would you like to run another test? y/n"


### Running tests on a set of maps

If the user answers "set" on the first question, 
next the program asks user for the absolute path of a folder containing map files.
Answer by typing ie "/home/yourname/Downloads/" and hitting enter.
When running tests on a set of maps, 
the program chooses first found vertex allowing movement in the upper corner of the map as starting point
and first found vertex allowing movement in the opposite side of the map as the goal.

After printing the results the program asks the user:
"would you like to run another test? y/n"


