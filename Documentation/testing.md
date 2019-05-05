# Testing

The application has been tested with automatized unit tests and integration tests using JUnit. The application has also been extensively tested manually, so that different user-scenarios would be covered. 

## Unit and integration tests

#### Application logic

All four classes in the domain-package which perform the application logic have been tested with unit and integration tests. These tests include checking that individual getters and setters work as they should as well as that the more complicated methods in the GameService-class work correctly. They check the actions that user may make in the game itself. Integration tests utilize the temporary FakeUserDao, FakeQuestionDao and FakeGameDao. Some of the GameService methods utilize many DAO-classes and this has been taken into account in the integration tests. 

The classes checking these:

- GameServiceTest
- GameTest
- QuestionTest
- UserText

#### DAO

The three different DAO-interfaces and the File-classes behind them have mainly been tested with integration tests. Temporary folders and text-files have been created to simulate the function of the actual DAO-classes. These temporary files are deleted after testing. DAO-tests are the following:

- FileGameDaoTest
- FileQuestionDaoTest
- FileUserDaoTest

#### Test coverage

The UI-package has been left out of the automated tests. The instruction coverage of automated tests is and the branch coverage is 

## System testing

The application has been also tested manually, as a user and as a admin. 

#### Installation and configuration 

The application has been downloaded and used in the way as the user manual describes into a Linux-environment. The testing included questions.txt and the config.properties.txt which were required. 

The application has been tested in situations when there are no readymade users.txt and games.txt -files as well as in the situation, where those files exist. 

#### Functions

All the functions described in the Requirements analysis have been gone through. Additionally different patterns have been tested, for instance when a new user is created, plays games, then checks the highscores, deletes their account, creates an account with the same username etc. Both user- and admin-functions have been checked. In-valid inputs have been tested is creating an account and creating new questions. 
