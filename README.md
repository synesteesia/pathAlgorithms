# pathAlgorithms

Project for [datastructure and algorithm](https://tiralabra.github.io/2020_p1/index) course at the University of Helsinki.

This program is used to compare three path finding algorithms - Dijkstra, A star and JPS. 
The program takes movinai map files and user text as input.
Map files can be found on following site:
https://www.movingai.com/benchmarks/grids.html 

The program prints various results comparing the algorithms as output.



## Links to documentation

### [Implementation Document](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/implementationDocument.md)
### [Instructions](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/Instructions.md)
### [Test document](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/testDocument.md)
### [Project definition](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/project_definition.md)




### Weekly reports

* [Weekly report #1](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/weeklyReports/Weekly_report_1.md)
* [Weekly report #2](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/weeklyReports/Weekly_report_2.md)
* [Weekly report #3](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/weeklyReports/Weekly_report_3.md)
* [Weekly report #4](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/weeklyReports/Weekly_report_4.md)
* [Weekly report #5](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/weeklyReports/Weekly_report_5.md)
* [Weekly report #6](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/weeklyReports/Weekly_report_6.md)

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

### Checkstyle

Checkstyle is configured in the file [checkstyle.xml](https://github.com/synesteesia/pathAlgorithms/blob/master/pathAlgorithms/checkstyle.xml).

Checkstyle is run with command


```
 mvn jxr:jxr checkstyle:checkstyle
```

Possible errors can be read by opening file _target/site/checkstyle.html_ with browser.

### JavaDoc

JavaDoc is generated with command

```
mvn javadoc:javadoc
```

JavaDoc can be read by opening file _target/site/apidocs/index.html_ with browser.

### Testing

Tests are run with command

```
mvn test
```
Testing report is created with command

```
mvn jacoco:report
```

Test report can be read by opening the file _target/site/jacoco/index.html_ with browser.



