/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.domain;

import finglish.domain.Question;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Game {
    
    private int id;
    private int accountId;
    private int questionCounter;
    private int correctAnswers;
    private int wrongAnswers;
    private Date date;
    
    public Game() {
        this.questionCounter = 0;
        this.correctAnswers = 0;
        this.wrongAnswers = 0;
        this.date = new Date();
    }
    
    public Game(int accountId, int correctAnswers, int questionCounter) {
        this.accountId = accountId;
        this.questionCounter = questionCounter;
        this.correctAnswers = correctAnswers;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getAccountId() {
        return this.accountId;
    }
    
    public int getAmountOfCorrectAnswers() {
        System.out.println(date);
        return correctAnswers;
    }
    
    public void setACorrectAnswer() {
        this.correctAnswers++;
        this.questionCounter++;
    }
    
    public void setAWrongAnswer() {
        this.wrongAnswers++;
        this.questionCounter++;
    }
    
    public int getQuestionCounter() {
        return questionCounter;
    }
      
}
