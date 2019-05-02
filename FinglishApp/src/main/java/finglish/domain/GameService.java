
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.domain;

import finglish.domain.Game;
import finglish.dao.GameDao;
import finglish.dao.QuestionDao;
import finglish.dao.UserDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

public class GameService {
    
    private int accountId;
    private ArrayList<Game> allGames;
    private ArrayList<Game> accountsGames;
    private ArrayList<Question> allQuestions;
    private ArrayList<User> allUsers;
    private ArrayList<Integer> usedIndexes;
    private Game game;
    private QuestionDao questionDao;
    private GameDao gameDao;
    private UserDao userDao;
    private Random random;
    private int index;

    public GameService(int accountId, GameDao gameDao, QuestionDao questionDao, UserDao userDao) {
        this.accountId = accountId;
        this.gameDao = gameDao;
        this.allGames = gameDao.getAll();
        this.questionDao = questionDao;
        this.allQuestions = questionDao.getAll();
        this.userDao = userDao;
        this.allUsers = userDao.getAll();
        this.random = new Random();
    }
  
    
    /**
    * Creates a new User-account.
    *
    * @param   username user's chosen username  
    * @param   password user's chosen password
    * 
    * @return information on whether the user was successfully added
    */
    
    public boolean addUser(String username, String password)  {
        System.out.println("täällä");
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, password);
        try {
            userDao.create(user);
        } catch(Exception e) {
            return false;
        }

        return true;
    }
  
    
    /**
    * Validates user's input.
    *
    * @param   input String that user has inserted
    * 
    * @return information on whether the input was of correct length
    */
    
    public boolean validateInput(String input) {
        if (input.length() < 3 || input.length() > 100) {
            return false;
        }
        return true;
    }
    
    
    /**
    * Creates a new question.
    * Adds a new question with the help of QuestionDao-class. 
    * 
    * @param   question user's inserted question
    * 
    */
 
    public void addQuestion(Question question) {
        this.allQuestions.add(question);
        for (Question q : allQuestions) {
            System.out.println(q.getQuestion());
        }
        try {
            this.questionDao.create(question);        
        } catch (Exception e) {
            System.out.println("Ei onnistunut kysymyksen lisääminen");
        }     
    }
    
    
    /**
    * Gets the next question for a game.
    * Calls for the ongoing game's question counter in order to know whether 10 questions is up.
    * Randomly selects a new question to show from all questions, in case question counter is between 0-9.
    * 
    * @return the next question to be shown to the player
    */

    public Question getTheNextQuestion() {

        if (this.game.getQuestionCounter() == 10) {
            return null;
        }
        
        if (this.game.getQuestionCounter() == 1) {
            this.usedIndexes.add(index);
        }

        index = this.random.nextInt(allQuestions.size() - 1);

        if (usedIndexes.size() != 1) {
            while (this.usedIndexes.contains(index)) {
                index = this.random.nextInt(allQuestions.size() - 1);
            }
        }

        Question question = allQuestions.get(index);
        question.shuffleOptions();
        this.usedIndexes.add(index);
         
        return question;
    }

    
    /**
    * Helps to keep score of the questions that were answered wrong in this game.
    */
     
    public void answerTheQuestion(boolean correctness) {
        if (correctness) {
            this.game.setACorrectAnswer();
        } else {
             this.game.setAWrongAnswer();
        }
    }
    
    
    /**
    * Starts a new quiz-game.
    * Initiates a new game with Game-class, and marks the game with the player's account id.
    */
    
    public void startANewGame() {
        this.game = new Game();
        this.game.setAccountId(accountId);
        this.usedIndexes = new ArrayList<>();
    }
    
    
    /**
    * Finishes the ongoing user's game.
    * Adds the game's results to permanent record keeping and clears the ongoing accounting for used questions.
    * 
    */

    public void finishAGame() {
        try {
            this.gameDao.create(this.game);
        } catch (Exception e) {
            System.out.println("Ei onnistunut pelin tallentaminen");
        }
        startANewGame();
    }
    
    
    /**
    * Counts the total score of the ongoing game.
    * 
    * @return a String of how many answers are correct out of ten.
    * 
    */
    
    
    public String getTotalScore() {
        return this.game.getAmountOfCorrectAnswers() + "/10 oikein";
    }


    /**
    * Tells what point the game is going at.
    * 
    * @return a String of how many questions have been answered out of ten.
    * 
    */

    public String getTheQuestionNumber() {
        return (this.game.getQuestionCounter() + 1) + "/10";
    }
    
    
    /**
    * Games for the account.
    * Initiates an ArrayList of games and adds all the games of the current account into it.
    * 
    * @param allGames takes in the data of all games for filtering.
    * 
    */
    
    public void getAccountsGames(ArrayList<Game> allGames) {
        this.accountsGames = new ArrayList<>();
        
        for (Game game : allGames) {
            if (game.getAccountId() == this.accountId) {
                accountsGames.add(game);
            }
        }   
    }
    
    public String highScoreList() {
        ArrayList<Integer> highScores = new ArrayList<>();
        for (Game game : this.accountsGames) {
            highScores.add(game.getAmountOfCorrectAnswers());
        }
        Collections.sort(highScores);
        String list = "";
        for (int i = 0; i < 10; i++) {
            list.concat((i+1) + ". " + highScores.get(i) + "/10");
        }
        
        return "Tämä";
    }


}
