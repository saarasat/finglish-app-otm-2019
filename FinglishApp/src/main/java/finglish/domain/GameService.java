/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.domain;

import finglish.domain.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import finglish.dao.QuestionDao;
import java.util.Random;

public class GameService {
    
    private ArrayList<Question> allQuestions;
    private ArrayList<Question> gameQuestions;
    private ArrayList<Question> usedQuestions;
    private Game game;
    private QuestionDao questionDao;
    private Random random;
    

    public GameService(QuestionDao questionDao) {
        this.questionDao = questionDao;
        this.allQuestions = questionDao.getAll();
        this.random = new Random();
    }

    public void addQuestion(Question question) {
        System.out.println(question.getCorrectAnswer());
        this.allQuestions.add(question);
        System.out.println(allQuestions.get(0));
    }

    public Question getTheNextQuestion() {
        int index = this.random.nextInt(9);
        Question question = gameQuestions.get(index);
        if (!usedQuestions.contains(index)) {
            usedQuestions.add(question);
            question.shuffleOptions();
        } else {
            getTheNextQuestion();
        }
        return question;
    }
    
    public void startANewGame() {
        this.gameQuestions = new ArrayList<>();
        this.usedQuestions = new ArrayList<>();
        System.out.println("this sixe " + allQuestions.size());
        for (int i = 0; i < 10; i++) {
           
            int index = this.random.nextInt(allQuestions.size());
            System.out.println("index " + index);
            Question question = allQuestions.get(index);
            
            if (!gameQuestions.contains(question)) {
                gameQuestions.add(question);
            }   
        }
        this.game = new Game(gameQuestions);
    }
    
    public void finishAGame() {
        this.gameQuestions.clear();
        this.usedQuestions.clear();
    }

}
