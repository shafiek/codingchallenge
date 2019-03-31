# Coding Challenge
This code base is an implementation for a coding challenge.

## Building
To build this project you will need to have Java 8+ installed.  This can be downloaded from Oracle's Java portal.

This project uses `gradle`, with the gradle wrapper script included.  To build, issue the following command

`./gradlew clean build`

This will generate a fat jar `build/libs/codingchallenge.jar`

The following gradle tasks are also supported

1. `checkstyleMain`: This will ensure that the source code adheres to the style defined in `config/checkstyle/checkstyle.xml`.  If the source code does not adhere to the style then the build will fail.
2. `jacocoTestReport`: Generates a unit test code coverage report in `build\jacocoHtml\index.html`.
3. `dependencyCheckAnalyze`: This will check that the dependencies do not have any security vulnerabilities (recorded as a CVE).  If any CVEs are found, the build will fail.
4. `pmdMain`: Uses PMD for static code analysis and will fail the build if there are any issues.

To run all of these you can issue the following command

`./gradlew clean checkstyleMain build jacocoTestReport dependencyCheckAnalyze pmdMain`

## Running
 
To run the code you can issue the following command

`java -jar ./build/libs/codingchallenge.jar -file sample.csv -accountId ACC334455 -from "20/10/2018 12:00:00" -to "20/10/2018 19:00:00"`

A sample csv file, sample.csv, has been included.

Running `java -jar ./build/libs/codingchallenge.jar` will print the usage:

```
Usage: java -jar {path to jar/codingchallenge.jar} -file [file] -accountId [accountId] -from [from date] -to [to date]
        -file           A CSV file in the Transaction list format defined by the coding challenge
        -accountId      The accountId for which you would like to print the details
        -from           The from Date Time in the format DD/MM/YYYY hh:mm:ss
        -to             The to Date Time in the format DD/MM/YYYY hh:mm:ss


Example: java -jar ./build/libs/codingchallenge.jar -file sample.csv -accountId ACC334455 -from "20/10/2018 12:00:00" -to "20/10/2018 19:00:00"
```

**Note**: the fat jar `build/libs/codingchallenge.jar` is included in this repository only for convenience.  It is not good practice to include a generated file in a source code repository.

## Possible Improvements

1. Perform much better error handling in a number of places including input arguments and reading from the csv file.
2. Fine tuning of the checkstyle file.
3. Fine tuning of the dependencyCheckAnalyze, so as not to fail for minor CVEs.
4. Setting up a CI server such as Jenkins or Bamboo to build on commit.
5. Setup the gradle release plugin to create releases (and tag) via the CI server.
6. Fail the build on a regression in unit test code coverage. 
