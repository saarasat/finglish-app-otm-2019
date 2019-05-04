#  User manual

Download the file finglishApp.jar

## Configuration

The program assumes that there is a file called config.properties in the project folder. All the games, users and questions will be saved and read according to that file. 

## Starting the application

The application can be started with the command:

<pre>
<code>java -jar finglishApp.jar</code>
</pre>

## Logging in and registering

#### Registering
In case you don't have a username and password readily defined, you can create them by hitting the "Luo uusi tunnus"-button. 


Next up you will see the following view. In order to register, you need to define a username and password. The password must be repeated to ensure it was spelled correctly. 

You can also define here whether you want the admin-rights by clicking on the "Ylläpitäjä" -checkbox. Administrators can do all the things as any user and in addition delete any account and add questions to the game. 

After you have successfully created an account you can click the "Kirjautumiseen"-button and go back to log in. 

#### Logging in

Here you need to write your username and password and hit the "Kirjaudu" -button in order to get into the app. 

## Regular User

As a regular user you will see the following main menu. You can play a quiz game by clicking "Pelaa", see the Top 10 games of all players by clicking "Top 10", remove your account by clicking "Poista tili" or log out by clicking "Kirjaudu ulos". 

#### Gameview

If you decide to play a game, you will be directed to the game view. 
- You can choose whichever option you think is correct by clicking that radiobutton. 
- Choosing one radiobutton will automatically check if it is correct and lets you know. 
- When you are ready for the next question you can just click the "Seuraava"-button and a the next question will randomly assigned to you. 
- At the end of the game you will see how many you got right in total. 
- Game can be stopped any time by clicking "Lopeta peli".

#### Deleting your account

You can delete your account by first clicking the "Poista tili"-button and then in the admin view confirm the removal. Your games will no longer show in the Top 10 -list after you delete your account.

## Admin

As a admin you have a few additional functions you can do.

#### Adding a question to the game

In this view you can add a new question to the game. You will need to define the question and four different answer options. Only one of the options can be right and needs to be repeated in the final field. 


#### Deleting other accounts

As an admin you have the possibility to delete any account you wish. Be careful however, you can also delete other admins here.

