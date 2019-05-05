# Software architecture

## Structure

The structure of the program follows a three-level architecture model and the packages are as follows:

![Packages](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/Packages.png)

## User interface

The UI contains seven different views:

- Logging in
- Creating a new user account
- Main menu
- Game view
- Highscore view
- Question addition view
- Admin view for deleting accounts

Each of these have been made as their own scene. All views have also their own classes apart from the Main menu. Main menu has been integrated into the [FinglishAppUi-class](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/ui/FinglishAppUi.java). The switching between different scenes happens also in the FinglishAppUi. By clicking on different buttons, the according scene will be set to the stage. UI has been separated into its own package [finglish.ui](https://github.com/saarasat/finglish-app-otm-2019/tree/master/FinglishApp/src/main/java/finglish/ui).

The UI calls for the methods in the [GameService-class](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/domain/GameService.java) to perform different functions. 

## Application logic

The classes which hold information changing in the application are [Question](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/domain/Question.java), [Game](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/domain/Game.java) and [User](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/domain/User.java). Each game has ten questions and each game belongs to a single user. A single question can appear in many games. 

Main part of the application logic is handled by the [GameService-class](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/domain/GameService.java). It offers several methods that are used during playing and creating games, questions and users. 

**For creating new users, questions and games:**

- public boolean public boolean addUser(String username, String password, int adminStatus)
- public void addQuestion(Question question)
- public boolean validateInput(String input)
- public void startANewGame()
- public void finishAGame()

**For deleting users:**

- public boolean removeUser(int id)

**For different happenings in the game:**

- public Question getTheNextQuestion()
- private int randomizer(int i)
- public void answerTheQuestion(boolean correctness)

In addition to these methods for handling the game, Question-, Game- and User-classes have simple getters and setters for checking the information relating to these objects. Question-class also handles the shuffling of different answer options.

GameService can access the package [finglish.dao](https://github.com/saarasat/finglish-app-otm-2019/tree/master/FinglishApp/src/main/java/finglish/dao) which is responsible for permanent keeping of user-, game- and question-data. The interfaces [GameDao](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/dao/GameDao.java), [UserDao](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/dao/UserDao.java) and [QuestionDao](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/dao/QuestionDao.java) save and read the text-files, which have the data relating to those classes.

The following architecture applies to GameService and the other classes:

![Architecture](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/Architecture.png)

## Permanent data storage

Package [finglish.dao](https://github.com/saarasat/finglish-app-otm-2019/tree/master/FinglishApp/src/main/java/finglish/dao) holds the classes GameDao, UserDao and QuestionDao. Out of these, the UserDao has the most methods for saving and also deleting data. At the moment, games and questions can only be created, but users can also be deleted. 

The classes act as interfaces for [FileGameDao-](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/dao/FileGameDao.java), [FileUser-](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/dao/FileUserDao.java) and [FileQuestionDao-classes](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/src/main/java/finglish/dao/FileQuestionDao.java) that read and save information to text-files. They all follow DAO-model and the GameService uses these classes only through the intefaces. Therefore they can be altered or replaced later on, for instance if there would be need to store the information to a database.

#### Files

The configuration file [config.properties](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/config.properties) defines the names of the files. The data is stored in the following format, all fields separated with ";" :


<pre>
users.txt
id;Username;Password;Admin-status
for example:
1;username;password;1
</pre>

<pre>
questions.txt
id;Question;Option1;Option2;Option3;Option4;CorrectAnswer
for example:
1;What is the name of this game?;Finglish;Swedish;English;Spanish;Finglish
</pre>

<pre>
games.txt
id;accountId;amountOfCorrectAnswers;TotalQuestionsPlayed
for example:
1;1;4;10
</pre>

## The main functions

Some of the most important functions described as sequence diagrams:

#### Creating a new user

A user writes in the username they wish to use as well as the password. Both of these will be validated, so that they are of the correct length. The password is also confirmed. When the inputs have been validated the UI calls for GameService to add the user. The GameService then checks that the username is unique by trying to find it with the help of the UserDao. If the username is unique the GameService will create a new User-object.

This User-object will be used as a parameter when the GameService calls for the UserDao to create the user. UserDao creates a random id for the User, sets it to the User-object and saves the new user to the text-file. If the user is successfully added, it will ve returned to the UserDao, which in return return boolean value "true" to the GameService. This way the GameService knows to show success-message to the Player and encourage them to log in. 

![Creating a new user](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/sequence_addingANewUser.png)


#### Starting a new game

A user clicks the "Pelaa"-button in the main menu which starts the process. The UI then initializes the GameView, which in turn tells the GameService to start a new Game. GameService creates a new Game-object. It also sets the id of the user playing as the accountId of the game, for future storing. 

UI then tells the GameService to the next question to show to the player. A randomizer-method is used to get a random Integer-value. The question with this Integer-value as index of the question-ArrayList, will be chosen as the next question. The GameService next checks that the game has not exceeded the 10-question-limit. The GameService will then call for the question to shuffle its answer options, increases the question counter of the game by one and returns the selected question to the UI. 

UI will then use the showQuestion() method to present the question to the player and sets gameScene to the Stage.

![Starting a New Game](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/sequence_startAGame.png)

#### Getting the next question

Will be done as described in the previous section. If the question counter of the game is already at 10 (thus, the maximum amount of questions for a game) a null value is returned.

![Getting the next question](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/sequence_getTheNextQuestion.png)

#### Answering the question correctly

Firstly the player interacts with the GameView and clicks on one of the radiobuttons. The UI then checks the text on that radiobutton and feeds it as a parameter to the checkIfCorrect-method when calling for the question at hand. The question will then match this option with the correct answer of the question, and if and when they are equal, return a boolean value "true".

UI will then notify the player by setting the answerCheck-Text as "Yay, oikein!". Then the UI will notify the GameService to increase the correct answer value of the game with one.

![Answer the question](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/sequence_answerTheQuestionCorrectly.png)

#### Deleting a user

Firstly the player clicks on the deleteButton of the user-account the wish to delete. The UI will then check the id of this user-account by getting it from the User-object. 

UI will then call for the GameService with the method removeUser() and feeds the user id as a parameter. GameService in turn will call for the userDao to first check that the user still exists and if so, then calls for UserDao to the delete the user with this id. UserDao removes the user from the list of users and saves the new, updated list into the userFile.

If this works correctly the GameService will return a boolean value "true" to the UI which in turn resets the userlist shown to the player. 

![Deleting a user](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/sequence_deletingAUser.png)

## Weaknesses in structure

#### UI

- The code in the intiating class FinglishAppUi can be hard to follow. Especially alternating between different scenes was complicated and it calls for a little clarification. 
- In the UI there are different panes utilized and in order to achieve a more uniform outlook, the usage of panes could be harmonized.
- The main menu could be also defined as its own class, or at least a method. This would clarify the changing between scenes. 

#### DAO-classes

- The DAO-classes do not perform full CRUD-functions. Updating information and deleting games and questions could be added. 
- At the moment the userId is being generated by randomizing an Integer, instead of creating a serial id. This was done in order to hinder the same id used for different games. This could have happened if a user is created, plays some games and then deleted. Randomizing works fine now, and the chances for having the same id are very minimal. But in case the application would utilize a database, this method for creating ids would need some reconsideration.

