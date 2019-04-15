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
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId() {
        return this.id;
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
