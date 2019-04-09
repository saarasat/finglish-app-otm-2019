# OTM course project - Finglish-app

## Description 
_A Finnish-English-gaming-app. App teaches people Finnish computer science -terminology in the form of a game._ 

## Documentation

[Hours worked on the project](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/Hours%20worked.md)

[Requirements analysis](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/Requirements%20analysis.md)

##Test credentials
The app can be logged in with these test credentials:
Username: "testaaja"
Password: "salasana"

## Command line functions

### Testing
Testing can be done with the command:

<pre><code>mvn test</code></pre>

Test coverage can be checked with the command:

<pre><code>mvn jacoco:report</code></pre>

You can check the test coverage report by opening the following in your browser: target/site/jacoco/index.html

### Executable jar

You can generate an executable jar with the command:

<pre><code>mvn package</code></pre>

this generates an executable jar-file into the target-folder: target/FinglishApp-1.0-SNAPSHOT.jar

### Checkstyle

Checks defined in checkstyle.xml can be run through with the command:

<pre><code>mvn jxr:jxr checkstyle:checkstyle</code></pre>

Possible errors you can see by viewing the following in your browser: target/site/checkstyle.html
