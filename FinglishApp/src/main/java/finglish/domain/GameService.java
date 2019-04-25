
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

public class GameService {
    
    private int account_id;
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

    public GameService(int account_id, GameDao gameDao, QuestionDao questionDao, UserDao userDao) {
        this.account_id = account_id;
        this.gameDao = gameDao;
        this.allGames = gameDao.getAll();
        this.questionDao = questionDao;
        this.allQuestions = questionDao.getAll();
        this.userDao = userDao;
        this.allUsers = userDao.getAll();
        this.random = new Random();
    }
    
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
    
    public boolean validateInput(String input) {
        if (input.length() < 3 || input.length() > 60) {
            return false;
        }
        return true;
    }
 
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
        this.usedIndexes.add(index);
         
        return question;
    }

    public void setWrongAnswer(Question question) {
        this.game.setAnAnsweredQuestion(question);
    }

    public void setCorrectAnswer(Question question) {
        this.game.setAnAnsweredQuestion(question);
        this.game.setACorrectAnswer();
    }

    public void startANewGame() {
        this.game = new Game();
        this.game.setAccount_Id(account_id);
        this.usedIndexes = new ArrayList<>();
    }

    public void finishAGame() {
        try {
            this.gameDao.create(this.game);
        } catch (Exception e) {
            System.out.println("Ei onnistunut pelin tallentaminen");
        }
        this.usedIndexes.clear();
        this.game = new Game();
    }

    public String getTotalScore() {
        return this.game.getAmountOfCorrectAnswers() + "/10 oikein";
    }
    
    public String getTheQuestionNumber() {
        return (this.game.getQuestionCounter() + 1) + "/10";
    }
    
    public void getAccountsGames(ArrayList<Game> allGames) {
        this.accountsGames = new ArrayList<>();
        
        for (Game game : allGames) {
            if (game.getAccountId() == this.account_id) {
                accountsGames.add(game);
            }
        }   
    }

}
