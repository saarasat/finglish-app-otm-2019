# OTM course project - Finglish-app

## Description 
_A Finnish-English-gaming-app. App teaches people Finnish computer science -terminology in the form of a game._ 

## Documentation

[Hours worked on the project](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/Hours%20worked.md)

[Requirements analysis](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/Requirements%20analysis.md)

## Command line functions

### Testing
Testing can be done with the command:

> mvn test

Test coverage can be checked with the command:

mvn jacoco:report

You can check the test coverage report by opening the following in your browser: target/site/jacoco/index.html

### Executable jar:

You can generate an executable jar with the command:

> mvn package

this generates an executable jar-file into the target-folder:

### JavaDoc

JavaDoc can be generated with the command:

> mvn javadoc:javadoc

You can check the JavaDoc by opening the following in your browser: target/site/apidocs/index.html

### Checkstyle

Checks defined in checkstyle.xml can be run through with the command:

> mvn jxr:jxr checkstyle:checkstyle

Possible errors you can see by viewing the following in your browser: target/site/checkstyle.html
