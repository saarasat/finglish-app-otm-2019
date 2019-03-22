# Finglish-app - Requirements analysis

## Purpose

The app is created as a tool for learning the vocabulary and abbreviations most commonly used in the field of computer science. Especially it focuses on those common slang of data communications (and the course Tietoliikenteen perusteet).

## How it works?

Finglish will work as a simple quiz game, where the user/player will be showed questions regarding the meaning or function of different concepts, words or abbreviations and he or she will choose from four different options the connect answer. The questions and answers will be in Finnish, but the terminology can naturally come from English. Hence the name “Finglish”

An example question can be for instance: _“Mikä on viestistä käytettävä nimitys TCP:n linkkikerroksella?” / “What is the name used for a message used on the link layer of TCP?_

## Userroles

### 1. User

#### Account management

* User can create an account to the application
* User can login with their account 
* User can logout from their account
* User can delete their account

#### Playing a game

* User can play a single game of ten questions at a time
* User will be shown a message of whether they got the question right or wrong 
* User will be shown the total result of a single game at the end of the game

#### _Additional features for future development_
#### _Total scores and stats_

* _User has the possibility to check the amount of games they have played_
* _User can check the overall score of how many they have gotten right in total in all games_
* _Users can check the scoreboard which includes the top 5 total scores of all the players_
* _Users can suggest a question to be added in the game_

### 2. Administrator

#### Account management

* A user with admin-rights can do all the same account management related things as an user 
* Administrator can also delete any account
* Administrator can see all the accounts as a list

#### Playing a game

* Administrator can also play a game and in addition similarly as a user and in addition administrator can create new questions for the game

#### Additional features for future development

* Administrator can grant admin-rights to any other account

## System requirements

* App needs to work on computers with a Linux or an OS X operating system.
* The data amount will be small enough to work fine on a single personal computer

