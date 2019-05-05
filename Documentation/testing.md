# Testing

The application has been tested with [automatized](https://github.com/saarasat/finglish-app-otm-2019/tree/master/FinglishApp/src/test/java/finglishapp) unit tests and integration tests using JUnit. The application has also been extensively tested manually, so that different user-scenarios would be covered. 


## Unit and integration tests

#### Application logic

All four classes in the [domain-package](https://github.com/saarasat/finglish-app-otm-2019/tree/master/FinglishApp/src/test/java/finglishapp/domain) which perform the application logic have been tested with unit and integration tests. These tests include checking that individual getters and setters work as they should, as well as that the more complicated methods in the GameService-class work correctly together. They check for possible actions that user may make in the game itself. Integration tests utilize the temporary [FakeUserDao](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/test/java/finglishapp/domain/FakeUserDao.java), [FakeQuestionDao](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/test/java/finglishapp/domain/FakeQuestionDao.java) and [FakeGameDao](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/test/java/finglishapp/domain/FakeGameDao.java). Some of the GameService methods utilize many DAO-classes and this has been taken into account in the integration tests. 

The classes checking these:

- [GameServiceTest](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/test/java/finglishapp/domain/GameServiceTest.java)
- [GameTest](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/test/java/finglishapp/domain/GameTest.java)
- [QuestionTest](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/test/java/finglishapp/domain/QuestionTest.java)
- [UserText](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/test/java/finglishapp/domain/UserTest.java)


#### DAO

The three different DAO-interfaces and the File-classes behind them have mainly been tested with integration tests. Temporary folders and text-files have been created to simulate the function of the actual DAO-classes. These temporary files are deleted after testing. DAO-tests are the following:

- [FileGameDaoTest](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/test/java/finglishapp/dao/FileGameDaoTest.java)
- [FileQuestionDaoTest](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/test/java/finglishapp/dao/FileQuestionDaoTest.java)
- [FileUserDaoTest](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/test/java/finglishapp/dao/FileUserDaoTest.java)


#### Test coverage

The UI-package has been left out of the automated tests. The instruction coverage of automated tests is 88 % and the branch coverage is 75 %. 

![Test coverage](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/testCoverage.png)


## System testing

The application has been also tested manually, as a user and as a admin. 


#### Installation and configuration 

The application has been downloaded and used in the way as the user manual describes into a Linux-environment. The testing included [questions.txt](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/questions.txt) and the [config.properties.txt](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/config.properties) which were required. 

The application has been tested in situations when there are no readymade users.txt and games.txt -files as well as in the situation, where those files exist. 


#### Functions

All the functions described in the [Requirements analysis](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/Requirements%20analysis.md) have been gone through. Additionally different patterns have been tested, for instance when a new user is created, plays games, then checks the highscores, deletes their account, creates an account with the same username etc. Both user- and admin-functions have been checked. Invalid inputs have been tested is creating an account and creating new questions. 
