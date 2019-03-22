# Finglish-app - Requirements analysis

## Purpose

The app is created as a tool for learning the vocabulary and abbreviations most commonly used in the field of computer science. The app especially focuses on those common slang of data communications (and the course Tietoliikenteen perusteet).

## How it works?

Finglish will work as a simple quiz game, where the user/player will be shown questions regarding the meaning or function of different concepts, words or abbreviations. The user will then choose from four different options the answer they think is correct. The questions and answers will be in Finnish, but the terminology can naturally come from English. Hence the name “Finglish-app”.

An example question can be for instance: _“Mikä on viestistä käytettävä nimitys TCP:n linkkikerroksella?” / “What is the name used for a message used on the link layer of TCP?_

## User roles

### 1. User

#### Account management

* User can create an account to the application
* User can login with their account 
* User can logout from their account
* User can delete their account

#### Playing the game

* User can play a single game of ten questions at a time
* User will be shown a message of whether they got the question right or wrong 
* User will be shown the total result of a single game at the end of the game

#### _Additional features for future development_
#### _Total scores and stats_

* _User has the possibility to check the amount of games they have played in total_
* _User can check the overall score of how many they have gotten right in all games played_
* _Users can check the scoreboard which includes the top 5 total scores of all the players_
* _Users can suggest a question to be added in the game_

### 2. Administrator

#### Account management

* A user with admin-rights can do all the same account management tasks as a user 
* Administrator can also delete any account
* Administrator can see all the accounts as a list

#### Playing the game

* Administrator can play a game like any user 
* In addition administrator can create new questions for the game

#### _Additional features for future development_

* _Administrator can grant admin-rights to any other user_

## System requirements

* App needs to work on computers with a Linux or an OS X operating system
* The data amount of the app will be small enough to work fine on a single personal computer

