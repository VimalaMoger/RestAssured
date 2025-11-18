
## Testing of [ThrillioWeb](https://github.com/VimalaMoger/ThrillioWeb/) APIs:

Dependencies added:
```text
    spring-boot-starter-web, spring-boot-devtools, Rest Assured, Hamcrest Matchers, TestNG, Gson library, io.cucumber and apache.poi libraries, jackson library, Lombok, maven-cucumber-reporting, maven-surefire-plugin, surefire-junit47
```


### Tasks

- Parameterize the API Tests with multiple data sets 
- Read static Json from an external file
- Pojo classes implementation
- Spec builder creation and logging
- Integrate Excel with Rest Assured - Data driven
- Integrate Cucumber with Rest Assured
- Maven mojo for the cucumber-reporting

<br>

- Jira REST APIs test
  	> Issue creation, update, read and delete request, send File Attachment

Run tests:
   
![restassured.PNG](assets/restassured.PNG)

### Cucumber tests from Maven cmd commands:

```
    mvn clean test
    mvn test -Dcucumber.options="--tags @addBook"
    mvn clean test -Dcucumber.filter.tags="@getBook"
    mvn test -Dcucumber.features="src/test/resources/features"
```

![cucumberTest](assets/cucumberMavenRun.PNG)


### Generated cucumber HTML reporting with plugin configuration

- Updated RunnerTest class with plugin configuration
- Added maven-cucumber-reporting plugin to pom.xml
- Ran mvn clean verify to generate the Cucumber report file

![cucumberTestReporting](assets/cucumberTestReporting.PNG)

[cucumber-html-reports](target/cucumber-report-html/cucumber-html-reports/feature-overview.html)

### Tips:

- Diff between mvn clean install and mvn clean test
    > _mvn clean install_ executes both clean and install phase ``` clean phase\install phase includes validate\compile\test\package\verify\install ```
    > _mvn test_ includes only phases up to test ``` validate\compile\test ```
- Dependency conflicts (JUnit vs. TestNG)
    > Maven Surefire plugin confused about which framework to use
      - Required both dependencies in this project
        - Solution: Force the Surefire provider to explicitly configure the maven-surefire-plugin to use JUnit
        - included org.apache.maven.surefire dependency in pom.xml plugin section

