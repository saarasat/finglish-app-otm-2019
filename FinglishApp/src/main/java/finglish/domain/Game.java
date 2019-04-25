/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.domain;

import finglish.domain.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    
    private int id;
    private int account_id;
    private List<Question> gameQuestions;
    private List<Question> answeredQuestions;
    private int questionCounter;
    private int correctAnswers;
    
    public Game() {
        this.gameQuestions = new ArrayList<>();
        this.answeredQuestions = new ArrayList<>();
        this.questionCounter = 0;
        this.correctAnswers = 0;
    }
    
    public Game(int account_id, int correctAnswers, int questionCounter) {
        this.gameQuestions = gameQuestions;
        this.answeredQuestions = answeredQuestions;
        this.account_id = account_id;
        this.questionCounter = questionCounter;
        this.correctAnswers = correctAnswers;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setAccount_Id(int account_id) {
        this.account_id = account_id;
    }

    
    public int getId() {
        return this.id;
    }
    
    public int getAccountId() {
        return this.account_id;
    }
    
    public int getAmountOfCorrectAnswers() {
        return correctAnswers;
    }
    
    public void setACorrectAnswer() {
        this.correctAnswers++;
    }
    
    public void setAnAnsweredQuestion(Question question) {
        answeredQuestions.add(question);
        questionCounter++;
        System.out.println(questionCounter);
    }
    
    public int getQuestionCounter() {
        return questionCounter;
    }
      
}
