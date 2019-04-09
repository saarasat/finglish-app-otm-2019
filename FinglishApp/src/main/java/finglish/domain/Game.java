/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    
    private int id; 
    private List<Question> gameQuestions;
    private List<Integer> answeredQuestions;
    private int questionCounter;
    
    public Game(ArrayList<Question> gameQuestions) {
        this.gameQuestions = gameQuestions;
        this.answeredQuestions = new ArrayList<>();
        this.questionCounter = 0;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getAmountOfCorrectAnswers() {
        return 5;
    }
    
    public void setAmountOfCorrectAnswers() {
        
    }
    
    public String getTotalScore() {
        return "5/10 oikein";
    }
            
    

      
}
