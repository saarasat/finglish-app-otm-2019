# Software architecture

## Structure

Structure of the program follows a three-level architecture model and the packages are as follows:

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

Each of these have been made as their own scene. All other views have their own classes apart from main menu. Main menu has been integrated into the FinglishAppUi-class where also switching between different scenes happens. By clicking on different buttons, the according scene will be set to the stage. UI has been separated into its own package finglish.ui.

The UI calls for the methods in the GameService-class to perform different functions. 

## Application logic

The classes which hold information of the game are Question, Game and User. Each game has ten questions and each game belongs to a single user. A question can appear in many games. 

Main part of the application logic is handled by the GameService-class. It offers several methods that are used during playing and creating games, questions and users. 

For creating new users, questions and games:

- public boolean public boolean addUser(String username, String password, int adminStatus)
- public void addQuestion(Question question)
- public boolean validateInput(String input)
- public void startANewGame()
- public void finishAGame()

For deleting users: 

- public boolean removeUser(int id)

For different happenings in the game: 

- public Question getTheNextQuestion()
- private int randomizer(int i)
- public void answerTheQuestion(boolean correctness)

GameService can access the package finglish.dao which is responsible for permanent keeping of users, games and questions. The interfaces GameDao, UserDao and QuestionDao save and read the text-files, which hold the data of those three classes.

The following architecture applies to GameService and the other classes:

![Architecture](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/Architecture.png)

## Permanent data storage

Package finglish.dao holds the classes GameDao, UserDao and QuestionDao. Out of these, the UserDao has the most methods for saving and also deleting data. At the moment, games and questions can only be created, users can also be deleted. 

The classes act as interfaces for FileGameDao-, FileUser- and FileQuestionDao-classes that read and save information to text-files. They all follow DAO-model and the GameService uses these classes only through the intefaces. Therefore they can be altered or replaced later on, for instance if there would be need to store the information to a database.

#### Files

The configuration file "config.properties" defines the names of the files. The data is stored in the following format, all fields separated with ";" :


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

#### Starting a new game
![Starting a New Game](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/SequenceDiagram-StartingAGame.png)

