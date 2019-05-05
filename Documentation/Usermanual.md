#  User manual

Download the file finglishApp.jar

## Configuration

The program assumes that there is a file called [config.properties](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/config.properties) in the project folder. All the games, users and questions will be saved and read according to that file. The game also has readily defined questions in the file [questions.txt](https://github.com/saarasat/finglish-app-otm-2019/blob/master/FinglishApp/questions.txt), make sure you have that ready as well.

## Starting the application

The application can be started with the command:

<pre>
<code>java -jar finglishApp.jar</code>
</pre>

## Logging in and registering

### Registering

In case you don't have a username and password, you can create them by hitting the "Luo uusi tunnus"-button. 

![Logging in](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/LoggingIn.png)

Next up you will see the following view. In order to register, you need to define a username and password. The password must be repeated to ensure it was spelled correctly. 

![Creating a new account](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/creatingAnAccount.png)

You can also define here whether you want the admin-rights by clicking on the "Ylläpitäjä" -checkbox. Administrators can do all the things as a regular user, and in addition delete any account they wish and add questions to the game. 

After you have successfully created an account you can click the "Kirjautumiseen"-button and go back to log in. 

### Logging in

Here you need to write your username and password and hit the "Kirjaudu" -button to get into the app.

![Logging in](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/LoggingIn.png)

## Regular User

As a regular user you will see the following main menu. You can play a quiz game by clicking "Pelaa", see the Top 10 games of all players by clicking "Top 10", remove your account by clicking "Poista tili" or log out by clicking "Kirjaudu ulos". 

![User main menu](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/userMainMenu.png)

### Gameview

If you decide to play a game, you will be directed to the game view. 
- You can choose whichever option you think is correct by clicking that radiobutton. 
- Choosing one radiobutton will automatically check if it is correct and lets you know. 
- When you are ready for the next question you can just click the "Seuraava"-button and a the next question will randomly assigned to you. 
- At the end of the game you will see how many you got right in total. 
- Game can be stopped any time by clicking "Lopeta peli".

![Game view](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/gameView.png)

### Deleting your account

You can delete your account by first clicking the "Poista tili"-button and then in the admin view confirm the removal. Your games will no longer show in the Top 10 -list after you delete your account.

![User admin view](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/adminView2.png)

## Admin

As a admin you have a few additional functions you can do.

### Adding a question to the game

In this view you can add a new question to the game. You will need to define the question and four different answer options. Only one of the options can be right and needs to be repeated in the final field. It does not matter which of the four is correct, as long as only one is.

![addQuestionsView](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/addQuestionsView.png)

### Deleting other accounts

As an admin you have the possibility to delete any account you wish. Be careful however, you can also delete other admins here.

![Admin view](https://github.com/saarasat/finglish-app-otm-2019/blob/master/Documentation/images/adminView1.png)
