# pathAlgorithms
Comparing pathfinding algorithms in java. Project for [datastructure and algorithm](https://tiralabra.github.io/2020_p1/index) course at the University of Helsinki.



## Links to documentation

### [Project definition](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/project_definition.md)



### Weekly reports

* [Weekly report #1](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/weeklyReports/Weekly_report_1.md)
* [Weekly report #2](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/weeklyReports/Weekly_report_2.md)
* [Weekly report #3](https://github.com/synesteesia/pathAlgorithms/blob/master/documentation/weeklyReports/Weekly_report_3.md)

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

Current test coverage 76%, 
some code should be ignored, for example Main class has redundant code.
Tests are run with command

```
mvn test
```
Testing report is created with command

```
mvn jacoco:report
```

Test report can be read by opening the file _target/site/jacoco/index.html_ with browser.



